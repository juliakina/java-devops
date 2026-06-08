package br.com.fiap.javaadv.blog.backend.domainmodel.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="TipoSolo_terrabyte")
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"plantios", "enderecos"})
@Builder
public class TipoSolo {
    @Id
    private @Getter @Setter UUID id;

    @NotBlank(message= "O nome é obrigatorio")
    @Size(min = 2, max=150, message="O nome deve ter entre 2 à 150 caracteres")
    @Column(name="NOME_sol", length = 150, nullable = false)
    private @Getter @Setter String nome;

    //RELACIONAMENTOS
    @JsonIgnore
    @ManyToMany(mappedBy = "tiposSolo")
    private @Getter @Setter Set<Plantio> plantios;

    @JsonIgnore
    @OneToMany(mappedBy = "tipoSolo")
    private Set<EnderecoPlantio> enderecos;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TipoSolo ent = (TipoSolo) o;
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
