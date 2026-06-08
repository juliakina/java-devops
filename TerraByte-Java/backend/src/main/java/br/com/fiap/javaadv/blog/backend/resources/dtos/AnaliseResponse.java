package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AnalisePlantio;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TipoSolo;
import lombok.*;

import java.sql.Date;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnaliseResponse {
    private @Getter @Setter UUID id;
    private @Getter @Setter Date data;
    private @Getter @Setter String nomeEndereco;
    private @Getter @Setter String nomePlantio;
    private @Getter @Setter String tipoSoloEndereco;
    private @Getter @Setter Set<String> tipoSoloPlantio;
    private @Getter @Setter String adequadoPlantio;
    private @Getter @Setter String nivelRisco;
    private @Getter @Setter double latitude;
    private @Getter @Setter double longitude;
    private @Getter @Setter double argila;
    private @Getter @Setter double areia;
    private @Getter @Setter double silte;
    private @Getter @Setter double raioKM;
    private @Getter @Setter String tempMin;
    private @Getter @Setter String tempMax;
    private @Getter @Setter String umidadeMed;
    private @Getter @Setter String recomendacao;


    public static AnaliseResponse toDto(final AnalisePlantio analisePlantio){
        return AnaliseResponse.builder()
                .id(analisePlantio.getId())
                .data(analisePlantio.getData())
                .nomeEndereco(analisePlantio.getEnderecoPlantio().getNome())
                .nomePlantio(analisePlantio.getPlantio().getNome())
                .adequadoPlantio(String.format("%.2f", analisePlantio.getAdequadoPlantio()) + "%")
                .nivelRisco(analisePlantio.getNivelRisco())
                .latitude(analisePlantio.getEnderecoPlantio().getLatitude())
                .longitude(analisePlantio.getEnderecoPlantio().getLongitude())
                .tipoSoloEndereco(analisePlantio.getEnderecoPlantio().getTipoSolo().getNome())
                .tipoSoloPlantio(analisePlantio.getPlantio()
                        .getTiposSolo()
                        .stream()
                        .map(TipoSolo::getNome)
                        .collect(Collectors.toSet()))
                .argila(analisePlantio.getEnderecoPlantio().getArgila())
                .areia(analisePlantio.getEnderecoPlantio().getAreia())
                .silte(analisePlantio.getEnderecoPlantio().getSilto())
                .raioKM(analisePlantio.getEnderecoPlantio().getRaioSoloKm())
                .tempMin(String.format("%.2f",analisePlantio.getTempMin()) + "°C")
                .tempMax(String.format("%.2f",analisePlantio.getTempMax()) + "°C")
                .umidadeMed(String.format("%.2f",analisePlantio.getUmidadeMed()) + "mm")
                .recomendacao(analisePlantio.getRecomendacao())
                .build();
    }
}
