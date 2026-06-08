package br.com.fiap.javaadv.blog.backend.services.interfaces;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AnalisePlantio;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.EnderecoPlantio;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Plantio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface AnaliseService {
    AnalisePlantio create(AnalisePlantio analisePlantio, String email);

    Page<AnalisePlantio> fetchAll(Pageable pageable);

    Page<AnalisePlantio> fetchAllByUsuario(String email, Pageable pageable);

    Optional<AnalisePlantio> fetchByIdAndUsuario(UUID id, String email);
}
