package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.TipoSoloRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TipoSolo;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Usuario;
import br.com.fiap.javaadv.blog.backend.services.interfaces.TipoSoloService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional( propagation = Propagation.REQUIRED)
public class TipoSoloServiceImp implements TipoSoloService {
    private final TipoSoloRepository tipoSoloRepository;

    @Override
    public TipoSolo create(TipoSolo tipoSolo){
        return this.tipoSoloRepository.save(tipoSolo);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Cacheable(value = "tipoSoloByIdCache", key = "#id")
    public Optional<TipoSolo> fetchById(UUID id){
        return this.tipoSoloRepository.findById(id);
    }


    @Override
    @Cacheable(value = "tipoSoloListCache", key = "#pageable.pageNumber")
    public Page<TipoSolo> fetchAll(Pageable pageable){
        return this.tipoSoloRepository.findAll(pageable);
    }
}
