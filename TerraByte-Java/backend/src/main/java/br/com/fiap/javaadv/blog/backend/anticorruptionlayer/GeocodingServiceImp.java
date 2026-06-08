package br.com.fiap.javaadv.blog.backend.anticorruptionlayer;

import br.com.fiap.javaadv.blog.backend.anticorruptionlayer.interfaces.GeocodingService;
import br.com.fiap.javaadv.blog.backend.resources.dtos.CoordenadaResponse;
import br.com.fiap.javaadv.blog.backend.resources.dtos.OpenMeteoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GeocodingServiceImp implements GeocodingService {
    private final RestTemplate restTemplate;

    @Override
    @Cacheable(value = "geocodingCache", key = "#cidade + '-' + #estado")
    public CoordenadaResponse buscarCoordenadas(String cidade, String estado) {

        String url =
                "https://geocoding-api.open-meteo.com/v1/search?name="
                        + cidade
                        + "&admin1="
                        + estado
                        + "&count=1&language=pt&format=json";

        OpenMeteoResponse response =
                restTemplate.getForObject(url, OpenMeteoResponse.class);

        if (response == null || response.getResults() == null || response.getResults().isEmpty()) {
            throw new RuntimeException("Cidade não encontrada.");
        }

        OpenMeteoResponse.Result result = response.getResults().getFirst();

        CoordenadaResponse coordenada = new CoordenadaResponse();

        coordenada.setLatitude(result.getLatitude());
        coordenada.setLongitude(result.getLongitude());

        return coordenada;
    }
}
