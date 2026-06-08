package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Usuario;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.SexoEnum;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioResponse {
    private @Getter @Setter UUID id;
    private @Getter @Setter String nome;
    private @Getter @Setter Date dataNascimento;
    private @Getter @Setter String telefone;
    private @Getter @Setter SexoEnum sexo;
    private @Getter @Setter String email;
    private @Getter @Setter String urlImg;

    public static UsuarioResponse toDto(final Usuario usuario) {

        return UsuarioResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .dataNascimento(usuario.getDataNascimento())
                .telefone(usuario.getTelefone())
                .email(usuario.getEmail())
                .sexo(usuario.getSexo())
                .urlImg(usuario.getUrlImg())
                .build();
    }
}

