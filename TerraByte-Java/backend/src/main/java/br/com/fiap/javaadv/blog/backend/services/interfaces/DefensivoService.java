package br.com.fiap.javaadv.blog.backend.services.interfaces;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Defensivo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface DefensivoService {
    Page<Defensivo> fetchAll(Pageable pageable);

    Optional<Defensivo> fetchById(UUID id);

    Page<Defensivo> fetchByTipo(String tipo, Pageable pageable);
}
