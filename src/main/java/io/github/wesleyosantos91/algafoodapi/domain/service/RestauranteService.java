package io.github.wesleyosantos91.algafoodapi.domain.service;

import io.github.wesleyosantos91.algafoodapi.domain.entity.Cozinha;
import io.github.wesleyosantos91.algafoodapi.domain.entity.Restaurante;
import io.github.wesleyosantos91.algafoodapi.domain.exception.ResourceNotFoundException;
import io.github.wesleyosantos91.algafoodapi.domain.repository.RestauranteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.text.MessageFormat.format;

@Service
public class RestauranteService {

    private final RestauranteRepository repository;
    private final CozinhaService cozinhaService;

    public RestauranteService(RestauranteRepository repository, CozinhaService cozinhaService) {
        this.repository = repository;
        this.cozinhaService = cozinhaService;
    }

    @Transactional
    public Restaurante save(Restaurante restaurante) {

        Cozinha cozinha = cozinhaService.findById(restaurante.getCozinha().getId());
        restaurante.setCozinha(cozinha);
        return repository.save(restaurante);
    }

    @Transactional
    public Restaurante update(Long id, Restaurante restaurante) {
        return repository.save(restaurante);
    }

    @Transactional
    public void delete(Long id) {

        Restaurante restaurante = findById(id);
        repository.delete(restaurante);
    }

    public List<Restaurante> findAll() {
        return repository.findAll();
    }

    public Restaurante findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Not found regitstry with code {0}", id)));
    }
}
