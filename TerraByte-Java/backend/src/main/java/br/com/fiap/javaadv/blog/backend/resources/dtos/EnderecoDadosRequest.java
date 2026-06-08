package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.EnderecoPlantio;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoDadosRequest {
    private @Getter @Setter String nome;

    public static EnderecoDadosRequest toDto(final EnderecoPlantio enderecoPlantio){
        return EnderecoDadosRequest.builder()
                .nome(enderecoPlantio.getNome())
                .build();
    }

    public static EnderecoPlantio toEntity(final EnderecoDadosRequest dto){
        return EnderecoPlantio.builder()
                .nome(dto.getNome())
                .build();
    }
}
