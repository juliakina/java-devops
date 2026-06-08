package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.UsuarioRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AuthUser;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Usuario;
import br.com.fiap.javaadv.blog.backend.services.interfaces.UsuarioService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
public class UsuarioServiceImp implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @CachePut(value = "usuarioCache", key = "#result.id")
    public Usuario create(Usuario usuario){
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return this.usuarioRepository.save(usuario);
    }

    @Override
    @CachePut(value = "usuarioCache", key = "#id")
    public Optional<Usuario> update(UUID id, Usuario patch) {
        return usuarioRepository.findById(id)
                .map(existing -> {
                    if (patch.getTelefone() != null)
                        existing.setTelefone(patch.getTelefone());

                    if (patch.getSenha() != null)
                        existing.setSenha(passwordEncoder.encode(patch.getSenha()));

                    if (patch.getUrlImg() != null)
                        existing.setUrlImg(patch.getUrlImg());

                    return usuarioRepository.save(existing);
                });
    }

    @Override
    @CachePut(value = "usuarioCache", key = "#result.get().id", condition = "#result.isPresent()")
    public Optional<Usuario> updateByEmail(String email, Usuario patch) {
        return usuarioRepository.findByEmail(email)
                .map(existing -> {

                    if (patch.getTelefone() != null)
                        existing.setTelefone(patch.getTelefone());

                    if (patch.getEmail() != null)
                        existing.setEmail(patch.getEmail());

                    if (patch.getSenha() != null)
                        existing.setSenha(passwordEncoder.encode(patch.getSenha()));

                    if (patch.getUrlImg() != null)
                        existing.setUrlImg(patch.getUrlImg());

                    return usuarioRepository.save(existing);
                });
    }

    @Override
    @CacheEvict(value = "usuarioCache", key = "#id")
    public void delete(UUID id){
        usuarioRepository.deleteById(id);
    }

    @Override
    public void deleteByEmail(String email) {

        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioRepository.delete(usuario);
    }

    @Override
    @Cacheable(value = "usuarioCache", key = "#id")
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Optional<Usuario> fetchById(UUID id){
        return this.usuarioRepository.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean existsById(UUID id){
        return this.usuarioRepository.existsById(id);
    }

    public Page<Usuario> fetchAll(Pageable pageable){
        System.out.println("CONSULTANDO O BANCO");
        return this.usuarioRepository.findAll(pageable);
    }

    @Override
    public Optional<Usuario> fetchEntityByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }


    @Override
    public UserDetails fetchByEmail(String username) throws UsernameNotFoundException {
        return this.usuarioRepository
                .findByEmail(username)
                .map(AuthUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found( email )" + username));
    }
}
