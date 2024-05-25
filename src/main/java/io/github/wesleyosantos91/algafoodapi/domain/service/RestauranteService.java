package io.github.wesleyosantos91.algafoodapi.domain.service;

import static java.text.MessageFormat.format;

import io.github.wesleyosantos91.algafoodapi.domain.entity.Cozinha;
import io.github.wesleyosantos91.algafoodapi.domain.entity.Restaurante;
import io.github.wesleyosantos91.algafoodapi.domain.exception.BusinessException;
import io.github.wesleyosantos91.algafoodapi.domain.exception.ResourceNotFoundException;
import io.github.wesleyosantos91.algafoodapi.domain.repository.RestauranteRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return saveAndUpdate(restaurante);
    }

    @Transactional
    public Restaurante update(Restaurante restaurante) {
        return saveAndUpdate(restaurante);
    }

    @Transactional
    public void delete(Long id) {

        final Restaurante restaurante = findById(id);
        repository.delete(restaurante);
    }

    public List<Restaurante> findAll() {
        return repository.findAll();
    }

    public Restaurante findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        format("Not found {0} registry with code {1}", Restaurante.class.getSimpleName(), id)));
    }

    private Restaurante saveAndUpdate(Restaurante restaurante) {
        try {
            final Cozinha cozinha = cozinhaService.findById(restaurante.getCozinha().getId());
            restaurante.setCozinha(cozinha);
            return repository.save(restaurante);
        } catch (ResourceNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}
