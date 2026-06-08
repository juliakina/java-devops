package br.com.fiap.javaadv.blog.backend.datasource.repositories;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Defensivo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DefensivoRepository extends JpaRepository<Defensivo, UUID> {
    Page<Defensivo> findByTipo(String tipo, Pageable pageable);
}
