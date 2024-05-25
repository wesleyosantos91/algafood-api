package io.github.wesleyosantos91.algafoodapi.domain.service;

import static java.text.MessageFormat.format;

import io.github.wesleyosantos91.algafoodapi.domain.entity.Cozinha;
import io.github.wesleyosantos91.algafoodapi.domain.exception.ResourceNotFoundException;
import io.github.wesleyosantos91.algafoodapi.domain.repository.CozinhaRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CozinhaService {

    private final CozinhaRepository repository;

    public CozinhaService(CozinhaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Cozinha save(Cozinha cozinha) {
        return saveAndUpdate(cozinha);
    }

    @Transactional
    public Cozinha update(Cozinha cozinha) {
        return saveAndUpdate(cozinha);
    }

    @Transactional
    public void delete(Long id) {

        final Cozinha cozinha = findById(id);
        repository.delete(cozinha);
    }

    public List<Cozinha> findAll() {
        return repository.findAll();
    }

    public Cozinha findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Not found {0} registry with code {1}", Cozinha.class.getSimpleName(), id)));
    }

    private Cozinha saveAndUpdate(Cozinha cozinha) {
        return repository.save(cozinha);
    }
}
