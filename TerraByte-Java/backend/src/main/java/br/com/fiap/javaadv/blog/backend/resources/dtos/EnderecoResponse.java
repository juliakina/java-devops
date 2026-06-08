package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.EnderecoPlantio;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.TipoSoloEnum;
import jakarta.persistence.Column;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoResponse {
    private @Getter @Setter UUID id;
    private @Getter @Setter String nome;
    private @Getter @Setter String cep;
    private @Getter @Setter String logradouro;
    private @Getter @Setter String cidade;
    private @Getter @Setter String estado;
    private @Getter @Setter double latitude;
    private @Getter @Setter double longitude;
    private @Getter @Setter String nomeSolo;
    private @Getter @Setter Double argila;
    private @Getter @Setter Double areia;
    private @Getter @Setter Double silto;
    private @Getter @Setter Double raioSoloKm;

    public static EnderecoResponse toDto(final EnderecoPlantio enderecoPlantio){
        return EnderecoResponse.builder()
                .id(enderecoPlantio.getId())
                .nome(enderecoPlantio.getNome())
                .logradouro(enderecoPlantio.getLogradouro())
                .cep(enderecoPlantio.getCep())
                .cidade(enderecoPlantio.getCidade())
                .estado(enderecoPlantio.getEstado())
                .latitude(enderecoPlantio.getLatitude())
                .longitude(enderecoPlantio.getLongitude())
                .nomeSolo(enderecoPlantio.getTipoSolo().getNome())
                .argila(enderecoPlantio.getArgila())
                .areia(enderecoPlantio.getAreia())
                .silto(enderecoPlantio.getSilto())
                .raioSoloKm(enderecoPlantio.getRaioSoloKm())
                .build();
    }
}
