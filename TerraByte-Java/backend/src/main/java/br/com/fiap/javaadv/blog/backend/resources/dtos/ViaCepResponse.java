package br.com.fiap.javaadv.blog.backend.resources.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViaCepResponse {
    private @Getter @Setter String cep;
    private @Getter @Setter String logradouro;
    private @Getter @Setter String complemento;
    private @Getter @Setter String bairro;
    private @Getter @Setter String localidade;
    private @Getter @Setter String estado;
}
