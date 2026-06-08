package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TipoSolo;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Usuario;
import br.com.fiap.javaadv.blog.backend.resources.dtos.*;
import br.com.fiap.javaadv.blog.backend.services.interfaces.TipoSoloService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tipoSolo")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class TipoSoloResource {
    private final TipoSoloService tipoSoloService;

    @GetMapping("/listar")
    public ResponseEntity<List<TipoSoloResponse>> fetchAll(@ParameterObject @PageableDefault(page = 0, size = 10,sort = "nome",
            direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(
                this.tipoSoloService.fetchAll(pageable)
                        .getContent()
                        .stream()
                        .map(TipoSoloResponse::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoSoloResponse> fetchById( @PathVariable UUID id ){
        return this.tipoSoloService.fetchById(id)
                .map(entidade -> ResponseEntity.ok(TipoSoloResponse.toDto(entidade)))
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }


    @GetMapping("/test-cache")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<String> testCache() {
        long start = System.currentTimeMillis();

        Page<TipoSolo> entidades = tipoSoloService.fetchAll(PageRequest.of(0, 1));

        long end = System.currentTimeMillis();
        long elapsed = end - start;

        System.out.println("Tempo de execução: " + elapsed + " ms (" + entidades.getTotalElements() + " TipoSolo)");
        return ResponseEntity.ok("Executado em " + elapsed + " ms. " + entidades.getTotalElements() + " TipoSolo encontrados.");
    }
}
