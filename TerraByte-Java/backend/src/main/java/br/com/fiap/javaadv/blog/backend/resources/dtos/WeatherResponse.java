package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.services.Daily;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class WeatherResponse {
    private @Getter @Setter Daily daily;
}
