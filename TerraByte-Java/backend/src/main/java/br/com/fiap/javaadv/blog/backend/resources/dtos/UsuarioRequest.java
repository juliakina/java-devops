package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Usuario;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.SexoEnum;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRequest {
    private @Getter @Setter String nome;
    private @Getter @Setter Date dataNascimento;
    private @Getter @Setter String telefone;
    private @Getter @Setter SexoEnum sexo;
    private @Getter @Setter String email;
    private @Getter @Setter String senha;

    public static UsuarioRequest toDto(final Usuario usuario){
        return UsuarioRequest.builder()
                .nome(usuario.getNome())
                .dataNascimento(usuario.getDataNascimento())
                .telefone(usuario.getTelefone())
                .sexo(usuario.getSexo())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .build();
    }

    public static Usuario toEntity(final UsuarioRequest dto){
        return Usuario.builder()
                .nome(dto.getNome())
                .dataNascimento(dto.getDataNascimento())
                .telefone(dto.getTelefone())
                .sexo(dto.getSexo())
                .email(dto.getEmail())
                .senha(dto.getSenha())
                .build();
    }
}
