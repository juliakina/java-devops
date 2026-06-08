package br.com.fiap.javaadv.blog.backend.anticorruptionlayer;

import br.com.fiap.javaadv.blog.backend.anticorruptionlayer.interfaces.ViaCepService;
import br.com.fiap.javaadv.blog.backend.resources.dtos.ViaCepResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ViaCepServiceImp implements ViaCepService {
    private final RestClient restClient;

    public ViaCepServiceImp() {
        this.restClient = RestClient.create();
    }

    @Cacheable(value="cepCache", key="#cep")
    public ViaCepResponse buscarCep(String cep) {
        return restClient
                .get()
                .uri("https://viacep.com.br/ws/{cep}/json/", cep)
                .retrieve()
                .body(ViaCepResponse.class);
    }
}
