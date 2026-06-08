package br.com.fiap.javaadv.blog.backend.resources.dtos;

import lombok.Getter;
import lombok.Setter;

public class CoordenadaResponse {
    private @Getter @Setter Double latitude;
    private @Getter @Setter Double longitude;
    private @Getter @Setter String name;
}
