package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TipoSolo;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoSoloRequest {
    private @Getter @Setter String nome;

    public static TipoSoloRequest toDto(final TipoSolo tipoSolo){
        return TipoSoloRequest.builder()
                .nome(tipoSolo.getNome())
                .build();
    }

    public static TipoSolo toEntity(final TipoSoloRequest dto){
        return TipoSolo.builder()
                .nome(dto.getNome())
                .build();
    }
}
