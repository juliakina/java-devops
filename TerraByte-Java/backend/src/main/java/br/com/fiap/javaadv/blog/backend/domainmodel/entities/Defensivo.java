package br.com.fiap.javaadv.blog.backend.domainmodel.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Date;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="Defensivo_terrabyte")
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "plantios")
@Builder
public class Defensivo {
    @Id
    private @Getter @Setter UUID id;

    @NotBlank(message= "O nome é obrigatorio")
    @Size(min = 2, max=100, message="O nome deve ter entre 2 à 100 caracteres")
    @Column(name="NOME_def", length = 100, nullable = false)
    private @Getter @Setter String nome;

    @NotBlank(message= "O tipo é obrigatorio")
    @Column(name="TIPO_def", length = 100, nullable = false)
    private @Getter @Setter String tipo;

    //RELACIONAMENTOS
    //N:N Plantio
    @JsonIgnore
    @ManyToMany(mappedBy = "defensivos")
    private @Getter @Setter Set<Plantio> plantios;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Defensivo ent = (Defensivo) o;
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
