package br.com.fiap.javaadv.blog.backend.resources.dtos;

import br.com.fiap.javaadv.blog.backend.domainmodel.enums.TipoSoloEnum;
import br.com.fiap.javaadv.blog.backend.domainmodel.services.SoilValues;
import lombok.Getter;
import lombok.Setter;

public class SoilGridsResultado {
    private @Getter @Setter SoilValues soilValues;
    private @Getter @Setter TipoSoloEnum tipoSolo;
    private @Getter @Setter double raioKm;

    public SoilGridsResultado(SoilValues soilValues, TipoSoloEnum tipoSolo, double raioKm) {
        this.soilValues = soilValues;
        this.tipoSolo = tipoSolo;
        this.raioKm = raioKm;
    }
}
