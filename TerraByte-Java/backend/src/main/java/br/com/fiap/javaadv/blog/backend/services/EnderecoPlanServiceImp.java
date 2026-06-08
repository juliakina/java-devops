package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.anticorruptionlayer.ViaCepServiceImp;
import br.com.fiap.javaadv.blog.backend.anticorruptionlayer.interfaces.GeocodingService;
import br.com.fiap.javaadv.blog.backend.anticorruptionlayer.interfaces.SoilGridsService;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.EnderecoPlantioRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.TipoSoloRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.UsuarioRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.EnderecoPlantio;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TipoSolo;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Usuario;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.TipoSoloEnum;
import br.com.fiap.javaadv.blog.backend.resources.dtos.CoordenadaResponse;
import br.com.fiap.javaadv.blog.backend.resources.dtos.SoilGridsResultado;
import br.com.fiap.javaadv.blog.backend.resources.dtos.ViaCepResponse;
import br.com.fiap.javaadv.blog.backend.services.interfaces.EnderecoPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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
public class EnderecoPlanServiceImp implements EnderecoPlanService {
    private final EnderecoPlantioRepository enderecoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ViaCepServiceImp viaCepServiceImp;
    private final GeocodingService geocodingService;
    private final SoilGridsService soilService;
    private final TipoSoloRepository tipoSoloRepository;


    @Override
    @CachePut(value = "enderecoCache", key = "#result.id")
    public EnderecoPlantio create(EnderecoPlantio end, String email){
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        end.setUsuario(usuario);

        ViaCepResponse viaCep = viaCepServiceImp.buscarCep(end.getCep());
        CoordenadaResponse coordenada = geocodingService.buscarCoordenadas(viaCep.getLocalidade(), viaCep.getEstado());
        end.preencherEndereco(viaCep, coordenada);

        SoilGridsResultado resultado = soilService.buscarTipoSolo(end.getLatitude(), end.getLongitude());
        TipoSolo tipoSolo = tipoSoloRepository.findByNome(resultado.getTipoSolo().name()).orElseThrow(() -> new RuntimeException("Tipo de solo não encontrado"));
        end.preencherSolo(resultado, tipoSolo);

        return enderecoRepository.save(end);
    }


    @Override
    @CachePut(value = "enderecoCache", key = "#id")
    public Optional<EnderecoPlantio> update(UUID id, EnderecoPlantio patch) {
        return enderecoRepository.findById(id)
                .map(existing -> {
                    if (patch.getNome() != null)
                        existing.setNome(patch.getNome());
                    return enderecoRepository.save(existing);
                });
    }



    @Override
    @CacheEvict(value="enderecoCache", key="#id")
    public void delete(UUID id){
        enderecoRepository.deleteById(id);
    }

    @Override
    @Cacheable(value="enderecoCache", key="#id")
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Optional<EnderecoPlantio> fetchById(UUID id){
        return this.enderecoRepository.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean existsById(UUID id){
        return this.enderecoRepository.existsById(id);
    }

    @Override
    public boolean existsByName(String nome){ return this.enderecoRepository.existsByNome(nome);}

    @Override
    public Page<EnderecoPlantio> fetchAll(Pageable pageable){
        return this.enderecoRepository.findAll(pageable);
    }

    @Override
    public Page<EnderecoPlantio> fetchAllByUsuario(String email, Pageable pageable) {
        return enderecoRepository.findByUsuarioEmail(email, pageable);
    }
}
