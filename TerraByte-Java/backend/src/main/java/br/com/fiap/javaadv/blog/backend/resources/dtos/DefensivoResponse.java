package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Defensivo;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DefensivoResponse {
    private @Getter @Setter UUID id;
    private @Getter @Setter String nome;
    private @Getter @Setter String tipo;

    public static DefensivoResponse toDto(final Defensivo defensivo){
        return DefensivoResponse.builder()
                .id(defensivo.getId())
                .nome(defensivo.getNome())
                .tipo(defensivo.getTipo())
                .build();
    }
}
