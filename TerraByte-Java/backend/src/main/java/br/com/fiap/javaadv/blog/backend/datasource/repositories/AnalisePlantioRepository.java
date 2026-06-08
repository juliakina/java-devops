package br.com.fiap.javaadv.blog.backend.datasource.repositories;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AnalisePlantio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AnalisePlantioRepository  extends JpaRepository<AnalisePlantio, UUID> {
    Page<AnalisePlantio> findByUsuarioEmail(String email, Pageable pageable);
    Optional<AnalisePlantio> findByIdAndUsuarioEmail(UUID id, String email);
}
