package br.com.fiap.javaadv.blog.backend.services.interfaces;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.EnderecoPlantio;
import br.com.fiap.javaadv.blog.backend.resources.dtos.SoilGridsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface EnderecoPlanService {
    EnderecoPlantio create(EnderecoPlantio endereco, String email);

    Optional<EnderecoPlantio> update(UUID id, EnderecoPlantio endereco);

    Page<EnderecoPlantio> fetchAll(Pageable pageable);

    Page<EnderecoPlantio> fetchAllByUsuario(String email, Pageable pageable);

    Optional<EnderecoPlantio> fetchById(UUID id);

    boolean existsById(UUID id);

    boolean existsByName(String nome);

    void delete(UUID id);
}
