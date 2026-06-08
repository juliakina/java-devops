package br.com.fiap.javaadv.blog.backend.domainmodel.services;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class Daily {
    private @Getter @Setter List<String> time;
    private @Getter @Setter List<Double> temperature_2m_max;
    private @Getter @Setter List<Double> temperature_2m_min;
    private @Getter @Setter List<Double> precipitation_sum;
}
