package br.com.fiap.javaadv.blog.backend.anticorruptionlayer;

import br.com.fiap.javaadv.blog.backend.resources.dtos.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class WeatherServiceImp {
    private final RestTemplate restTemplate = new RestTemplate();

    @Cacheable(value="weatherCache", key="#latitude + '-' + #longitude")
    public WeatherResponse getForecast(double lat, double lon) {

        String url = String.format(
                "https://api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s&daily=temperature_2m_max,temperature_2m_min,precipitation_sum&forecast_days=16&timezone=auto",
                lat, lon
        );

        return restTemplate.getForObject(url, WeatherResponse.class);
    }
}
