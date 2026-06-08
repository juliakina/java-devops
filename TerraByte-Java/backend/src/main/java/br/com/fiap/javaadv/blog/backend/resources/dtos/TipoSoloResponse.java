package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TipoSolo;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoSoloResponse {
    private @Getter @Setter UUID id;
    private @Getter @Setter String nome;

    public static TipoSoloResponse toDto(final TipoSolo tipoSolo){
        return TipoSoloResponse.builder()
                .id(tipoSolo.getId())
                .nome(tipoSolo.getNome())
                .build();
    }
}
