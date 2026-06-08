package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AnalisePlantio;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Defensivo;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TipoSolo;
import br.com.fiap.javaadv.blog.backend.resources.dtos.AnaliseRequest;
import br.com.fiap.javaadv.blog.backend.resources.dtos.AnaliseResponse;
import br.com.fiap.javaadv.blog.backend.resources.dtos.TipoSoloRequest;
import br.com.fiap.javaadv.blog.backend.resources.dtos.TipoSoloResponse;
import br.com.fiap.javaadv.blog.backend.services.interfaces.AnaliseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
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
@RequestMapping("/api/analise")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class AnaliseResource {
    private final AnaliseService analiseService;

    @PostMapping
    public ResponseEntity<AnaliseRequest> create(Authentication authentication, @RequestParam UUID idEndereco, @RequestParam UUID idPlantio){
        String email = authentication.getName();
        var request = AnaliseRequest.builder().idEndereco(idEndereco).idPlantio(idPlantio).build();

        AnalisePlantio entidade = request.toEntity(request);
        AnalisePlantio savedEntity = this.analiseService.create(entidade, email);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedEntity.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(request.toDto(savedEntity));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<AnaliseResponse>> fetchAll(Authentication authentication, @ParameterObject @PageableDefault(page = 0, size = 10, sort = "data", direction = Sort.Direction.DESC) Pageable pageable){
        String email = authentication.getName();
        return ResponseEntity.ok(
                analiseService.fetchAllByUsuario(email, pageable)
                        .getContent()
                        .stream()
                        .map(AnaliseResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnaliseResponse> fetchById(@PathVariable UUID id, Authentication authentication) {
        String email = authentication.getName();

        return analiseService.fetchByIdAndUsuario(id, email)
                .map(entidade -> ResponseEntity.ok(AnaliseResponse.toDto(entidade)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/test-cache")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<String> testCache() {
        long start = System.currentTimeMillis();

        Page<AnalisePlantio> entidades = analiseService.fetchAll(PageRequest.of(0, 1));

        long end = System.currentTimeMillis();
        long elapsed = end - start;

        System.out.println("Tempo de execução: " + elapsed + " ms (" + entidades.getTotalElements() + " AnalisePlantio)");
        return ResponseEntity.ok("Executado em " + elapsed + " ms. " + entidades.getTotalElements() + " AnalisePlantio encontrados.");
    }
}
