package br.com.fiap.javaadv.blog.backend.anticorruptionlayer;

import br.com.fiap.javaadv.blog.backend.anticorruptionlayer.interfaces.SoilGridsService;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.TipoSoloEnum;
import br.com.fiap.javaadv.blog.backend.domainmodel.services.SoilValues;
import br.com.fiap.javaadv.blog.backend.resources.dtos.SoilGridsResponse;
import br.com.fiap.javaadv.blog.backend.resources.dtos.SoilGridsResultado;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SoilGridsServiceImp implements SoilGridsService {

    private final RestTemplate restTemplate;

    @Override
    @Cacheable(value = "soloCache", key = "#lat + '-' + #lon")
    public SoilGridsResultado buscarTipoSolo(double lat, double lon) {

        SoilGridsResultado resultado = buscarComRaio(lat, lon);

        if (resultado == null) {
            return new SoilGridsResultado(new SoilValues(0,0,0), TipoSoloEnum.DESCONHECIDO, 0);
        }

        TipoSoloEnum tipoSolo = classificar(resultado.getSoilValues().getClay(), resultado.getSoilValues().getSand(), resultado.getSoilValues().getSilt());
        resultado.setTipoSolo(tipoSolo);

        return resultado;
    }

    private SoilGridsResultado buscarComRaio(double lat, double lon) {

        double[] offsets = {0.0, 0.015, -0.015, 0.05, -0.05, 0.1, -0.1, 0.2, -0.2};
        for (double latOffset : offsets) {
            for (double lonOffset : offsets) {
                SoilValues soil = buscarSolo(lat + latOffset, lon + lonOffset);
                if (soil != null) {
                    double raioKm = Math.sqrt(Math.pow(latOffset * 111, 2) + Math.pow(lonOffset * 111, 2));
                    return new SoilGridsResultado(soil, null, Math.round(raioKm * 100.0) / 100.0);
                }
            }
        }

        return null;
    }

    private SoilValues buscarSolo(double lat, double lon) {
        try {
            String url = "https://rest.isric.org/soilgrids/v2.0/properties/query" +
                            "?lat=" + lat +
                            "&lon=" + lon +
                            "&property=clay" +
                            "&property=sand" +
                            "&property=silt" +
                            "&depth=0-5cm" +
                            "&value=mean";

            SoilGridsResponse response =
                    restTemplate.getForObject(url, SoilGridsResponse.class);

            if (response == null || response.getProperties() == null || response.getProperties().getLayers() == null) {
                return null;
            }

            List<SoilGridsResponse.Layers> layers = response.getProperties().getLayers();

            Double clay = getValue(layers, "clay");
            Double sand = getValue(layers, "sand");
            Double silt = getValue(layers, "silt");

            if (clay == null || sand == null || silt == null) {
                return null;
            }

            return new SoilValues(clay, sand, silt);

        } catch (Exception e) {
            return null;
        }
    }

    private Double getValue(List<SoilGridsResponse.Layers> layers, String type) {

        return layers.stream()
                .filter(l -> type.equalsIgnoreCase(l.getName()))
                .findFirst()
                .map(layer -> {

                    if (layer.getDepths() == null || layer.getDepths().isEmpty()) {
                        return null;
                    }

                    if (layer.getDepths().get(0).getValues() == null) {
                        return null;
                    }

                    return layer.getDepths()
                            .get(0)
                            .getValues()
                            .getMean();
                })
                .orElse(null);
    }

    private TipoSoloEnum classificar(double clay, double sand, double silt) {
        clay /= 10.0;
        sand /= 10.0;
        silt /= 10.0;

        if (clay >= 60)
            return TipoSoloEnum.MUITO_ARGILOSA;

        if (clay >= 40 && silt >= 40)
            return TipoSoloEnum.ARGILO_SILTOSA;

        if (clay >= 35 && sand >= 45)
            return TipoSoloEnum.ARGILO_ARENOSA;

        if (clay >= 40)
            return TipoSoloEnum.ARGILA;

        if (clay >= 27 && silt >= 40)
            return TipoSoloEnum.FRANCO_ARGILO_SILTOSA;

        if (clay >= 27 && sand >= 45)
            return TipoSoloEnum.FRANCO_ARGILO_ARENOSA;

        if (clay >= 27)
            return TipoSoloEnum.FRANCO_ARGILOSA;

        if (silt >= 80)
            return TipoSoloEnum.SILTE;

        if (silt >= 50)
            return TipoSoloEnum.FRANCO_SILTOSA;

        if (sand >= 85)
            return TipoSoloEnum.AREIA;

        if (sand >= 70)
            return TipoSoloEnum.AREIA_FRANCA;

        if (sand >= 52)
            return TipoSoloEnum.FRANCO_ARENOSO;

        return TipoSoloEnum.FRANCA;
    }
}