package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.EnderecoPlantio;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Plantio;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Usuario;
import br.com.fiap.javaadv.blog.backend.resources.dtos.*;
import br.com.fiap.javaadv.blog.backend.services.interfaces.EnderecoPlanService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/endereco")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class EnderecoResource {
    private final EnderecoPlanService enderecoPlanService;

    @PostMapping
    public ResponseEntity<EnderecoRequest> create(Authentication authentication, @Valid @RequestBody EnderecoRequest request){
        String email = authentication.getName();

        if(this.enderecoPlanService.existsByName(request.getNome())) throw new RuntimeException("Nome já registrado!");

        EnderecoPlantio entidade = request.toEntity(request);
        EnderecoPlantio savedEntity = enderecoPlanService.create(entidade, email);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedEntity.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(request.toDto(savedEntity));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EnderecoDadosRequest> update(@PathVariable UUID id, @Valid @RequestBody EnderecoDadosRequest dadosDto){
        return this.enderecoPlanService.update(id, EnderecoDadosRequest.toEntity(dadosDto))
                .map(entidade ->
                        ResponseEntity.ok(EnderecoDadosRequest.toDto(entidade)))
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        if( this.enderecoPlanService.existsById(id)) {
            this.enderecoPlanService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/listar")
    public ResponseEntity<List<EnderecoResponse>> fetchAll(Authentication authentication, @ParameterObject @PageableDefault(page = 0, size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
        String email = authentication.getName();
        return ResponseEntity.ok(
                enderecoPlanService
                        .fetchAllByUsuario(email, pageable)
                        .getContent()
                        .stream()
                        .map(EnderecoResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponse> fetchById( @PathVariable UUID id ){
        return this.enderecoPlanService.fetchById(id)
                .map(entidade -> ResponseEntity.ok(EnderecoResponse.toDto(entidade)))
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @GetMapping("/test-cache")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<String> testCache() {
        long start = System.currentTimeMillis();

        Page<EnderecoPlantio> entidades = enderecoPlanService.fetchAll(PageRequest.of(0, 1));

        long end = System.currentTimeMillis();
        long elapsed = end - start;

        System.out.println("Tempo de execução: " + elapsed + " ms (" + entidades.getTotalElements() + " EnderecoPlantio)");
        return ResponseEntity.ok("Executado em " + elapsed + " ms. " + entidades.getTotalElements() + " EnderecoPlantio encontrados.");
    }
}
