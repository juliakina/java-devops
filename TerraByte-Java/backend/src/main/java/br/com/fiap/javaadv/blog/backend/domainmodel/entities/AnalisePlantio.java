package br.com.fiap.javaadv.blog.backend.domainmodel.entities;

import br.com.fiap.javaadv.blog.backend.resources.dtos.WeatherResponse;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name="AnalisePlantio_terrabyte")
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"usuario", "plantio", "enderecoPlantio"})
@Builder
public class AnalisePlantio {
    @Id
    private @Getter @Setter UUID id;

    @Column(name="DT_ana")
    private @Getter @Setter Date data;

    @Column(name="TEMP_MIN_ana")
    private @Getter @Setter double tempMin;

    @Column(name="TEMP_MAX_ana")
    private @Getter @Setter double tempMax;

    @Column(name="UMIDADE_MED_ana")
    private @Getter @Setter double umidadeMed;

    @Column(name="ADEQ_ana")
    private @Getter @Setter double adequadoPlantio;

    @Column(name="NVL_risc_ana")
    private @Getter @Setter String nivelRisco;

    @Column(name="REC_ana",length = 1000)
    private @Getter @Setter String recomendacao;



    //RELACIONAMENTOS
    //N:1 Usuario
    @ManyToOne
    @JoinColumn(name="ID_usu_(PK)")
    private @Getter @Setter Usuario usuario;

    //N:1 Plantio
    @ManyToOne
    @JoinColumn(name="ID_plan_(PK)")
    private @Getter @Setter Plantio plantio;

    //N:1 Endereco
    @ManyToOne
    @JoinColumn(name="ID_end_(PK)")
    private  @Getter @Setter EnderecoPlantio enderecoPlantio;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AnalisePlantio ent = (AnalisePlantio) o;
        return Objects.equals(id, ent.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @PrePersist
    public void gerarId() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    private static double calcularMediaMax(WeatherResponse weather) {
        return Optional.ofNullable(weather.getDaily())
                .map(d -> d.getTemperature_2m_max())
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0);
    }
    private static double calcularMediaMin(WeatherResponse weather) {
        return Optional.ofNullable(weather.getDaily())
                .map(d -> d.getTemperature_2m_min())
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0);
    }
    private static double calcularChuvaPrevista(WeatherResponse weather) {
        return Optional.ofNullable(weather.getDaily())
                .map(d -> d.getPrecipitation_sum())
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .mapToDouble(Double::doubleValue)
                .sum();
    }
    private static double calcularClimaScore(double mediaMax, double mediaMin, Plantio plantio) {
        double diferencaMax = Math.abs(mediaMax - plantio.getTempMax());
        double diferencaMin = Math.abs(mediaMin - plantio.getTempMin());
        double score = 100;

        score -= diferencaMax * 2;
        score -= diferencaMin * 2;

        return Math.clamp(score, 0, 100);
    }
    private static double calcularUmidadeScore(double chuvaPrevista, Plantio plantio) {
        double aguaNecessaria = plantio.getAguaMM();
        double score = 100;

        if (aguaNecessaria > 0) {
            double diferencaAgua = Math.abs(chuvaPrevista - aguaNecessaria);
            score = 100 - ((diferencaAgua / aguaNecessaria) * 100);
        }

        return Math.clamp(score, 0, 100);
    }
    private static boolean verificarSoloCompativel(Plantio plantio, EnderecoPlantio endereco) {
        return Optional.ofNullable(plantio.getTiposSolo())
                .orElse(Collections.emptySet())
                .stream()
                .anyMatch(tp -> tp.getNome().equalsIgnoreCase(endereco.getTipoSolo().getNome()));
    }
    private static double calcularPenaltyEpoca(Plantio plantio) {
        Set<Month> mesesIdeais = Optional.ofNullable(plantio.getMesesIdeais()).orElse(Collections.emptySet());
        Month mesAtual = LocalDate.now().getMonth();

        if (!mesesIdeais.isEmpty() && !mesesIdeais.contains(mesAtual)) {
            return 25;
        }

        return 0;
    }
    private static double calcularScoreFinal(double soloScore, double climaScore,double umidadeScore, Plantio plantio, boolean soloCompativel) {
        double score = (soloScore * 0.45) + (climaScore * 0.35) + (umidadeScore * 0.20);
        score -= calcularPenaltyEpoca(plantio);
        score = Math.clamp(score, 0, 100);

        if (!soloCompativel) {
            score = Math.min(score, 40);
        }

        return score;
    }
    private static String calcularNivel(double scoreFinal) {
        if (scoreFinal >= 80) {
            return "MUITO_PROVAVEL";
        }

        if (scoreFinal >= 65) {
            return "ALTA";
        }

        if (scoreFinal >= 45) {
            return "MEDIA";
        }

        return "BAIXA";
    }
    private static String gerarMesesTexto(Plantio plantio) {
        return Optional.ofNullable(plantio.getMesesIdeais())
                .orElse(Collections.emptySet())
                .stream()
                .map(m -> switch (m) {
                    case JANUARY -> "Janeiro";
                    case FEBRUARY -> "Fevereiro";
                    case MARCH -> "Março";
                    case APRIL -> "Abril";
                    case MAY -> "Maio";
                    case JUNE -> "Junho";
                    case JULY -> "Julho";
                    case AUGUST -> "Agosto";
                    case SEPTEMBER -> "Setembro";
                    case OCTOBER -> "Outubro";
                    case NOVEMBER -> "Novembro";
                    case DECEMBER -> "Dezembro";
                })
                .sorted()
                .collect(Collectors.joining(", "));
    }
    private static String gerarDefensivosTexto(Plantio plantio) {
        return Optional.ofNullable(plantio.getDefensivos())
                .orElse(Collections.emptySet())
                .stream()
                .map(def -> def.getNome() + " (" + def.getTipo() + ")")
                .sorted()
                .collect(Collectors.joining(", "));
    }
    private static String gerarRecomendacao(Plantio plantio, double mediaMin, double mediaMax, double chuvaPrevista, boolean soloCompativel, double scoreFinal, String nivel) {
        return "Cultura analisada: " + plantio.getNome() + " | " +
                "Compatibilidade com o solo: " +
                (soloCompativel ? "ALTA" : "BAIXA") + " | " +
                "Temperatura previsa para a região: " +
                " - Mínima media: " +
                String.format("%.2f", mediaMin) + "°C " +
                " - Máxima média: " +
                String.format("%.2f", mediaMax) + "°C | " +
                "Faixa ideal da cultura: " +
                plantio.getTempMin() + "°C até " +
                plantio.getTempMax() + "°C | " +
                "Chuva prevista para os próximos dias: " +
                chuvaPrevista + " | " +
                "Necessidade hídrica da cultura: " +
                plantio.getAguaMM() + " | " +
                "Melhor época para plantio: " +
                gerarMesesTexto(plantio) + " | " +
                "Defensivo indicado: " +
                gerarDefensivosTexto(plantio) + " | " +
                "Índice final de adequação: " +
                String.format("%.2f", scoreFinal) + " | " +
                "Classificação: " + nivel;
    }


    public static AnalisePlantio gerarAnalise(Usuario usuario, EnderecoPlantio endereco, Plantio plantio, WeatherResponse weather) {
        double mediaMax = calcularMediaMax(weather);
        double mediaMin = calcularMediaMin(weather);
        double chuvaPrevista = calcularChuvaPrevista(weather);

        double climaScore = calcularClimaScore(mediaMax, mediaMin, plantio);

        double umidadeScore = calcularUmidadeScore(chuvaPrevista, plantio);

        boolean soloCompativel = verificarSoloCompativel(plantio, endereco);

        double soloScore = soloCompativel ? 100 : 0;

        double scoreFinal = calcularScoreFinal(soloScore, climaScore, umidadeScore, plantio, soloCompativel);

        String nivel = calcularNivel(scoreFinal);

        String recomendacao = gerarRecomendacao(plantio, mediaMin,mediaMax, chuvaPrevista, soloCompativel, scoreFinal, nivel);

        return AnalisePlantio.builder()
                .usuario(usuario)
                .data(Date.valueOf(LocalDate.now()))
                .enderecoPlantio(endereco)
                .plantio(plantio)
                .tempMin(mediaMin)
                .tempMax(mediaMax)
                .umidadeMed(chuvaPrevista)
                .adequadoPlantio(scoreFinal)
                .nivelRisco(nivel)
                .recomendacao(recomendacao)
                .build();
    }
}
