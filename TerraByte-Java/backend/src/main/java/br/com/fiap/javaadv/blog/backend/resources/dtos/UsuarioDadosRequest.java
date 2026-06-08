package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Usuario;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDadosRequest {
    private @Getter @Setter String telefone;
    private @Getter @Setter String senha;
    private @Getter @Setter String urlImg;

    public static UsuarioDadosRequest toDto(final Usuario usuario){
        return UsuarioDadosRequest.builder()
                .telefone(usuario.getTelefone())
                .senha(usuario.getSenha())
                .urlImg(usuario.getUrlImg())
                .build();
    }

    public static Usuario toEntity(final UsuarioDadosRequest dto){
        return Usuario.builder()
                .telefone(dto.getTelefone())
                .senha(dto.getSenha())
                .urlImg(dto.getUrlImg())
                .build();
    }
}
