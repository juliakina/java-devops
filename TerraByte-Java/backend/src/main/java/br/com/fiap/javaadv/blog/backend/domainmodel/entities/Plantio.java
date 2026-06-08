package br.com.fiap.javaadv.blog.backend.domainmodel.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Month;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="Plantio_terrabyte")
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"defensivos", "tiposSolo", "analisesPlantio"})
@Builder
public class Plantio {
    @Id
    private @Getter @Setter UUID id;

    @NotBlank(message= "O nome é obrigatorio")
    @Size(min = 2, max=150, message="O nome deve ter entre 2 à 150 caracteres")
    @Column(name="NOME_plan", length = 150, nullable = false)
    private @Getter @Setter String nome;

    @NotNull(message = "Temperatura Minima é obrigatoria")
    @Column(name="TEMP_MIN_plan", nullable = false)
    private @Getter @Setter double tempMin;

    @NotNull(message = "Temperatura Maxima é obrigatoria")
    @Column(name="TEMP_MAX_plan", nullable = false)
    private @Getter @Setter double tempMax;

    @NotNull(message = "MM da Agua é obrigatoria")
    @Column(name="AGUA_MM_plan", nullable = false)
    private @Getter @Setter double aguaMM;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="meses_ideais_terrabyte", joinColumns=@JoinColumn(name="id_plan_fk"))
    @Column(name="mes_ideal", nullable = false)
    @Enumerated(EnumType.STRING)
    private @Getter @Setter Set<Month> mesesIdeais;

    @Size(min = 4, max=100, message="A Url deve ter entre 4 à 100 caracteres")
    @Column(name="URL_IMG_plan", length = 100)
    private @Getter @Setter String urlImg;

    //RELACIONAMENTOS
    //N:N Defensivo
    @ManyToMany
    @JoinTable(
            name="plan_def_terrabyte",
            joinColumns = @JoinColumn(name="id_plan_fk"),
            inverseJoinColumns = @JoinColumn(name="id_def_fk")
    )
    private @Getter @Setter Set<Defensivo> defensivos;

    //N:N Tipo Solo
    @ManyToMany
    @JoinTable(
            name="plan_tp_terrabyte",
            joinColumns = @JoinColumn(name="id_plan_fk"),
            inverseJoinColumns = @JoinColumn(name="id_solo_fk")
    )
    private @Getter @Setter Set<TipoSolo> tiposSolo;

    //1:N Analise
    @JsonIgnore
    @OneToMany(mappedBy = "plantio", fetch = FetchType.LAZY)
    private @Getter @Setter Set<AnalisePlantio> analisesPlantio;



    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Plantio ent = (Plantio) o;
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
}