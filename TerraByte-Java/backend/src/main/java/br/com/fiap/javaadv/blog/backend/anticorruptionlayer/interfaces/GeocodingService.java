package br.com.fiap.javaadv.blog.backend.anticorruptionlayer.interfaces;

import br.com.fiap.javaadv.blog.backend.resources.dtos.CoordenadaResponse;

public interface GeocodingService {
    CoordenadaResponse buscarCoordenadas(String cidade, String estado);
}
