package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.EnderecoPlantio;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoRequest {
    private @Getter @Setter String nome;
    private @Getter @Setter String cep;

    public static EnderecoRequest toDto(final EnderecoPlantio enderecoPlantio){
        return EnderecoRequest.builder()
                .nome(enderecoPlantio.getNome())
                .cep(enderecoPlantio.getCep())
                .build();
    }

    public static EnderecoPlantio toEntity(final EnderecoRequest dto){
        return EnderecoPlantio.builder()
                .nome(dto.getNome())
                .cep(dto.getCep())
                .build();
    }
}
