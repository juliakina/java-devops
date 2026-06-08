package br.com.fiap.javaadv.blog.backend.infrastructure.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(
                "usuarioCache",
                "tipoSoloByIdCache",
                "tipoSoloListCache",
                "plantioByIdCache",
                "plantioListCache",
                "plantioTipoSoloCache",
                "plantioDefensivoCache",
                "enderecoCache",
                "enderecoUsuarioCache",
                "defensivoByIdCache",
                "defensivoListCache",
                "defensivoTipoCache",
                "analiseByIdCache",
                "analiseByIdCache",
                "analiseUsuarioCache",
                "analiseUsuarioIdCache",
                "geocodingCache",
                "soloCache",
                "cepCache",
                "weatherCache"
                );
    }

}