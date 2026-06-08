package br.com.fiap.javaadv.blog.backend.domainmodel.entities;

import br.com.fiap.javaadv.blog.backend.resources.dtos.CoordenadaResponse;
import br.com.fiap.javaadv.blog.backend.resources.dtos.SoilGridsResultado;
import br.com.fiap.javaadv.blog.backend.resources.dtos.ViaCepResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="EnderecoPlantio_terrabyte")
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"tipoSolo", "analises"})
@Builder
public class EnderecoPlantio {
    @Id
    private @Getter @Setter UUID id;

    @Size(min = 2, max=300, message="O nome deve ter entre 2 à 300 caracteres")
    @Column(name="NOME_end", length = 300)
    private @Getter @Setter String nome;

    @NotBlank(message= "O CEP é obrigatorio")
    @Size(min = 8, max=9, message="O CEP deve ter entre 8 à 9 caracteres")
    @Column(name="CEP_end", length = 10, nullable = false)
    private @Getter @Setter String cep;

    @Size(max=300, message="O logradouro deve ter entre 2 à 100 caracteres")
    @Column(name="LOGRAD_end", length = 100)
    private @Getter @Setter String logradouro;

    @Size(min = 2, max=100, message="O cidade deve ter entre 2 à 100 caracteres")
    @Column(name="CIDADE_end", length = 100)
    private @Getter @Setter String cidade;

    @Size(min = 2, max=100, message="O estado deve ter entre 2 à 100 caracteres")
    @Column(name="ESTADO_end", length = 100)
    private @Getter @Setter String estado;

    @Size(min = 2, max=100, message="O bairro deve ter entre 2 à 100 caracteres")
    @Column(name="BAIRRO_end", length = 100)
    private @Getter @Setter String bairro;

    @Column(name="LAT_end")
    private @Getter @Setter double latitude;

    @Column(name="LONG_end")
    private @Getter @Setter double longitude;

    @Column(name="ARGILA_end", length = 100)
    private @Getter @Setter double argila;

    @Column(name="AREIA_end", length = 100)
    private @Getter @Setter double areia;

    @Column(name="SILTO_end", length = 100)
    private @Getter @Setter double silto;

    @Column(name="RAIO_end", length = 100)
    private @Getter @Setter double raioSoloKm;

    //RELACIONAMENTOS
    //N:1 Tipo solo
    @ManyToOne
    @JoinColumn(name = "ID_SOLO_FK")
    private @Getter @Setter TipoSolo tipoSolo;

    //1:N Analise
    @JsonIgnore
    @OneToMany(mappedBy = "enderecoPlantio", fetch = FetchType.LAZY)
    private @Getter @Setter Set<AnalisePlantio> analises;

    //N:1 Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO_FK", nullable = false)
    private @Getter @Setter Usuario usuario;


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EnderecoPlantio ent = (EnderecoPlantio) o;
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

    public void preencherEndereco(
            ViaCepResponse viaCep,
            CoordenadaResponse coordenada
    ) {

        if (viaCep.getLogradouro() == null) {
            throw new RuntimeException("CEP não encontrado.");
        }

        this.logradouro = viaCep.getLogradouro();
        this.cidade = viaCep.getLocalidade();
        this.estado = viaCep.getEstado();

        this.latitude = coordenada.getLatitude();
        this.longitude = coordenada.getLongitude();
    }

    public void preencherSolo(
            SoilGridsResultado resultado,
            TipoSolo tipoSolo
    ) {

        this.raioSoloKm = resultado.getRaioKm();

        this.argila = resultado.getSoilValues().getClay();
        this.areia = resultado.getSoilValues().getSand();
        this.silto = resultado.getSoilValues().getSilt();

        this.tipoSolo = tipoSolo;
    }
}
