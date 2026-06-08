package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AnalisePlantio;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.EnderecoPlantio;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Plantio;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Usuario;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnaliseRequest {
    private @Getter @Setter UUID idEndereco;
    private @Getter @Setter UUID idPlantio;

    public static AnaliseRequest toDto(final AnalisePlantio analisePlantio){
        return AnaliseRequest.builder()
                .idEndereco(analisePlantio.getEnderecoPlantio().getId())
                .idPlantio(analisePlantio.getPlantio().getId())
                .build();
    }

    public static AnalisePlantio toEntity(final AnaliseRequest dto){
        return AnalisePlantio.builder()
                .enderecoPlantio(EnderecoPlantio.builder().id(dto.idEndereco).build())
                .plantio(Plantio.builder().id(dto.idPlantio).build())
                .build();
    }
}
