package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Usuario;
import br.com.fiap.javaadv.blog.backend.resources.dtos.UsuarioDadosRequest;
import br.com.fiap.javaadv.blog.backend.resources.dtos.UsuarioRequest;
import br.com.fiap.javaadv.blog.backend.resources.dtos.UsuarioResponse;
import br.com.fiap.javaadv.blog.backend.services.interfaces.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioResource {
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioRequest> create(@Valid @RequestBody UsuarioRequest request ){
        Usuario entidade = request.toEntity(request);
        Usuario savedEntity = this.usuarioService.create(entidade);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedEntity.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(request.toDto(savedEntity));
    }


    @SecurityRequirement(name = "bearerAuth")
    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioDadosRequest> update(@PathVariable UUID id, @Valid @RequestBody UsuarioDadosRequest dadosDto){
        return this.usuarioService.update(id, UsuarioDadosRequest.toEntity(dadosDto))
                .map(entidade ->
                        ResponseEntity.ok(UsuarioDadosRequest.toDto(entidade)))
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }


    @GetMapping("/infos") //up
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<UsuarioResponse> infos(Authentication authentication){


        String email = authentication.getName();

        return usuarioService.fetchEntityByEmail(email)
                .map(usuario -> ResponseEntity.ok(UsuarioResponse.toDto(usuario)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        if( this.usuarioService.existsById(id)) {
            this.usuarioService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<UsuarioResponse> fetchById( @PathVariable UUID id ){
        return this.usuarioService.fetchById(id)
                .map(entidade -> ResponseEntity.ok(UsuarioResponse.toDto(entidade)))
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @GetMapping("/listar")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<UsuarioResponse>> fetchAll(@ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable){
        return ResponseEntity.ok(
                this.usuarioService.fetchAll(pageable)
                        .getContent()
                        .stream()
                        .map(UsuarioResponse::toDto)
                        .collect(Collectors.toList())
        );
    }


    @GetMapping("/test-cache")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<String> testCache() {
        long start = System.currentTimeMillis();

        Page<Usuario> entidades = usuarioService.fetchAll(PageRequest.of(0, 1));

        long end = System.currentTimeMillis();
        long elapsed = end - start;

        System.out.println("Tempo de execução: " + elapsed + " ms (" + entidades.getTotalElements() + " Usuario)");
        return ResponseEntity.ok("Executado em " + elapsed + " ms. " + entidades.getTotalElements() + " Usuario encontrados.");
    }
}
