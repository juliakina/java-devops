package br.com.fiap.javaadv.blog.backend.domainmodel.services;

import lombok.Getter;
import lombok.Setter;

public class SoilValues {

    private @Getter @Setter double clay;
    private @Getter @Setter double sand;
    private @Getter @Setter double silt;

    public SoilValues(double clay, double sand, double silt) {
        this.clay = clay;
        this.sand = sand;
        this.silt = silt;
    }
}