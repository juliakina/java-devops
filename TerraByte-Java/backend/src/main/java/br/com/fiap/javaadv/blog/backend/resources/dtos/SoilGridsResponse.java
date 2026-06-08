package br.com.fiap.javaadv.blog.backend.resources.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class SoilGridsResponse {
    private @Getter @Setter Properties properties;

    public static class Properties {
        private @Getter @Setter  List<Layers> layers;
    }

    public static class Layers {
        private @Getter @Setter  String name;
        private @Getter @Setter  List<Depths> depths;
    }

    public static class Depths {
        private @Getter @Setter  Values values;
    }

    public static class Values {
        private @Getter @Setter  Double mean;
    }
}
