package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Defensivo;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DefensivoRequest {
    private @Getter @Setter String nome;

    private @Getter @Setter String tipo;

    public static DefensivoRequest toDto(final Defensivo defensivo){
        return DefensivoRequest.builder()
                .nome(defensivo.getNome())
                .tipo(defensivo.getTipo())
                .build();
    }

    public static Defensivo toEntity(final DefensivoRequest defensivoRequest){
        return Defensivo.builder()
                .nome(defensivoRequest.getNome())
                .tipo(defensivoRequest.getTipo())
                .build();
    }
}
