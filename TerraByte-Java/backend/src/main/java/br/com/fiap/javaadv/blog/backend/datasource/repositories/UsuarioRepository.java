package br.com.fiap.javaadv.blog.backend.datasource.repositories;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Usuario;
import jakarta.persistence.QueryHint;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository  extends JpaRepository<Usuario, UUID> {
    @QueryHints(@QueryHint(name="org.hibernate.cacheable", value="true"))
    Optional<Usuario> findByEmail(String email);
}
