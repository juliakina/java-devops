package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Plantio;

import lombok.*;

import java.time.Month;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlantioRequest {
    private @Getter @Setter String nome;
    private @Getter @Setter double tempMin;
    private @Getter @Setter double tempMax;
    private @Getter @Setter double aguaMM;
    private @Getter @Setter Set<Month> mesesIdeas;
    private @Getter @Setter String urlImg;

    public static PlantioRequest toDto(final Plantio plantio){
        return PlantioRequest.builder()
                .nome(plantio.getNome())
                .tempMin(plantio.getTempMin())
                .tempMax(plantio.getTempMax())
                .aguaMM(plantio.getAguaMM())
                .mesesIdeas(plantio.getMesesIdeais())
                .urlImg(plantio.getUrlImg())
                .build();
    }

    public static Plantio toEntity(final PlantioRequest dto){
        return Plantio.builder()
                .nome(dto.getNome())
                .tempMin(dto.getTempMin())
                .tempMax(dto.getTempMax())
                .aguaMM(dto.getAguaMM())
                .mesesIdeais(dto.getMesesIdeas())
                .urlImg(dto.getUrlImg())
                .build();
    }
}
