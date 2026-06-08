package br.com.fiap.javaadv.blog.backend.datasource.repositories;


import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Plantio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PlantioRepository  extends JpaRepository<Plantio, UUID> {
    @Query("""
    SELECT DISTINCT p
    FROM Plantio p
    JOIN p.tiposSolo ts
    WHERE ts.id = :tipoSoloId
    """)
    Page<Plantio> findByTipoSolo(@Param("tipoSoloId") UUID tipoSoloId, Pageable pageable);


    @Query("""
    SELECT DISTINCT p
    FROM Plantio p
    JOIN p.defensivos d
    WHERE d.id = :defensivoId
    """)
    Page<Plantio> findByDefensivo(
            @Param("defensivoId") UUID defensivoId,
            Pageable pageable
    );
}
