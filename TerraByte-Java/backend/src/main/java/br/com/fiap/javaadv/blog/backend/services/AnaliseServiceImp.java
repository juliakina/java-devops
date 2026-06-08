package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.anticorruptionlayer.WeatherServiceImp;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.AnalisePlantioRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.EnderecoPlantioRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.PlantioRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.UsuarioRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AnalisePlantio;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.EnderecoPlantio;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Plantio;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Usuario;
import br.com.fiap.javaadv.blog.backend.resources.dtos.WeatherResponse;
import br.com.fiap.javaadv.blog.backend.services.interfaces.AnaliseService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional( propagation = Propagation.REQUIRED)
public class AnaliseServiceImp implements AnaliseService {
    private final AnalisePlantioRepository analiseRepository;
    private final UsuarioRepository usuarioRepository;
    private final WeatherServiceImp weatherServiceImp;
    private final EnderecoPlantioRepository enderecoRepository;
    private final PlantioRepository plantioRepository;

    @Override
    @CachePut(value = "analiseByIdCache", key = "#result.id")
    public AnalisePlantio create(AnalisePlantio analise, String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        EnderecoPlantio endereco = enderecoRepository.findById(analise.getEnderecoPlantio().getId())
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        Plantio plantio = plantioRepository.findById(analise.getPlantio().getId())
                .orElseThrow(() -> new RuntimeException("Plantio não encontrado"));

        WeatherResponse weather = weatherServiceImp.getForecast(endereco.getLatitude(), endereco.getLongitude());

        return analiseRepository.save(AnalisePlantio.gerarAnalise(usuario, endereco, plantio, weather));
    }


    @Override
    public Page<AnalisePlantio> fetchAll(Pageable pageable){
        return this.analiseRepository.findAll(pageable);
    }

    @Override
    @Cacheable(value = "analiseUsuarioCache", key = "#email + '-' + #pageable.pageNumber")
    public Page<AnalisePlantio> fetchAllByUsuario(String email, Pageable pageable) {
        return analiseRepository.findByUsuarioEmail(email, pageable);
    }

    @Override
    @Cacheable(value = "analiseUsuarioIdCache", key = "#email + '-' + #id")
    public Optional<AnalisePlantio> fetchByIdAndUsuario(UUID id, String email) {
        return analiseRepository.findByIdAndUsuarioEmail(id, email);
    }
}
