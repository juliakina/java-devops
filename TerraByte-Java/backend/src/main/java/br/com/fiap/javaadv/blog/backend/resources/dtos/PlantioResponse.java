package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Defensivo;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Plantio;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TipoSolo;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.TipoSoloEnum;
import lombok.*;

import java.time.Month;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlantioResponse {
    private @Getter @Setter UUID id;
    private @Getter @Setter String nome;
    private @Getter @Setter double tempMin;
    private @Getter @Setter double tempMax;
    private @Getter @Setter double aguaMM;
    private @Getter @Setter Set<String> tiposDeSolo;
    private @Getter @Setter Set<Month> mesesIdeas;
    private @Getter @Setter Set<String >defensivo;
    private @Getter @Setter String urlImg;

    public static PlantioResponse toDto(final Plantio plantio){
        return PlantioResponse.builder()
                .id(plantio.getId())
                .nome(plantio.getNome())
                .tempMin(plantio.getTempMin())
                .tempMax(plantio.getTempMax())
                .aguaMM(plantio.getAguaMM())
                .tiposDeSolo(plantio.getTiposSolo()
                                .stream()
                                .map(TipoSolo::getNome)
                                .collect(Collectors.toSet()))
                .defensivo(plantio.getDefensivos()
                        .stream()
                        .map(Defensivo::getNome)
                        .collect(Collectors.toSet()))
                .mesesIdeas(plantio.getMesesIdeais())
                .urlImg(plantio.getUrlImg())
                .build();
    }
}
