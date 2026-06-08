package br.com.fiap.javaadv.blog.backend.anticorruptionlayer.interfaces;

import br.com.fiap.javaadv.blog.backend.resources.dtos.SoilGridsResultado;

public interface SoilGridsService {
    SoilGridsResultado buscarTipoSolo(double lat, double lon);
}
