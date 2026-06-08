package br.com.fiap.javaadv.blog.backend.resources.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class OpenMeteoResponse {
    private @Getter @Setter List<Result> results;


    @Getter @Setter
    public static class Result {
        private Double latitude;
        private Double longitude;
    }
}
