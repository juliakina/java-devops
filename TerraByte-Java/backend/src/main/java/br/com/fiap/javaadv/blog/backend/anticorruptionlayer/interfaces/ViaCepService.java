package br.com.fiap.javaadv.blog.backend.anticorruptionlayer.interfaces;

import br.com.fiap.javaadv.blog.backend.resources.dtos.ViaCepResponse;

public interface ViaCepService {
    ViaCepResponse buscarCep(String cep);
}
