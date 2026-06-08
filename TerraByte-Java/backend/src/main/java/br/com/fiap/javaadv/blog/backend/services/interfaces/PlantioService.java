package br.com.fiap.javaadv.blog.backend.services.interfaces;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Plantio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface PlantioService {
    Plantio create(Plantio plantio);

    Page<Plantio> fetchAll(Pageable pageable);

    Optional<Plantio> fetchById(UUID id);

    Page<Plantio> fetchByTipoSolo(UUID tipoSoloId, Pageable pageable);

    Page<Plantio> fetchByDefensivo(UUID defensivoId, Pageable pageable);
}
