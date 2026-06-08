package br.com.fiap.javaadv.blog.backend.datasource.repositories;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.EnderecoPlantio;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EnderecoPlantioRepository  extends JpaRepository<EnderecoPlantio, UUID> {
    boolean existsByNome (String nome);

    Page<EnderecoPlantio> findByUsuarioEmail(String email, Pageable pageable);
}
