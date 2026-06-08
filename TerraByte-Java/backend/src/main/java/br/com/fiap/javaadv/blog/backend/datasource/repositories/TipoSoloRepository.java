package br.com.fiap.javaadv.blog.backend.datasource.repositories;


import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TipoSolo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TipoSoloRepository  extends JpaRepository<TipoSolo, UUID> {
    Optional<TipoSolo> findByNome(String nome);
}
