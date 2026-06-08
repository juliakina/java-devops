package br.com.fiap.javaadv.blog.backend.services.interfaces;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TipoSolo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface TipoSoloService {
    TipoSolo create(TipoSolo tipoSolo);

    Page<TipoSolo> fetchAll(Pageable pageable);

    Optional<TipoSolo> fetchById(UUID id);

}
