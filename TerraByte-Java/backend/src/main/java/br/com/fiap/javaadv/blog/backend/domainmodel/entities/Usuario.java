package br.com.fiap.javaadv.blog.backend.domainmodel.entities;

import br.com.fiap.javaadv.blog.backend.domainmodel.enums.SexoEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Date;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="Usuario_terrabyte")
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "analises")
@Builder
public class Usuario {
    @Id
    private @Getter @Setter UUID id;

    @NotBlank(message= "O nome é obrigatorio")
    @Size(min = 2, max=100, message="O nome deve ter entre 2 à 100 caracteres")
    @Column(name="NOME_usu", length = 100, nullable = false)
    private @Getter @Setter String nome;

    @NotNull(message= "A data de nascimento é obrigatoria")
    @Column(name="DATA_NASC_usu", nullable = false)
    private @Getter @Setter Date dataNascimento;

    @NotBlank(message= "O telefone é obrigatorio")
    @Size(min = 11, max=11, message="O telefone deve ter 11 caracteres")
    @Column(name="TEL_usu", length = 11, nullable = false)
    private @Getter @Setter String telefone;

    @NotNull(message= "O sexo é obrigatorio")
    @Column(name="SEXO_usu", length = 1, nullable = false)
    private @Getter @Setter SexoEnum sexo;

    @NotBlank(message= "O email é obrigatorio")
    @Size(min = 2, max=100, message="O email deve ter entre 2 à 100 caracteres")
    @Column(name="EMAIL_usu", length = 100, nullable = false)
    private @Getter @Setter String email;

    @NotBlank(message= "A senha é obrigatoria")
    @Size(min = 8, max=100, message="O senha deve ter entre 8 à 30 caracteres")
    @Column(name="SENHA_usu", length = 100, nullable = false)
    private @Getter @Setter String senha;

    @Size(min = 4, max=100, message="A Url deve ter entre 4 à 100 caracteres")
    @Column(name="URL_IMG_usu", length = 100)
    private @Getter @Setter String urlImg;

    //RELACIONAMENTOS
    //1:N Analise
    @JsonIgnore
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private @Getter @Setter Set<AnalisePlantio> analises;

    //1:N endereço
    @JsonIgnore
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private @Getter @Setter Set<EnderecoPlantio> enderecos;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario ent = (Usuario) o;
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
