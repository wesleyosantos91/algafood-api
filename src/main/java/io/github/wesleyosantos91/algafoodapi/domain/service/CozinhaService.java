package io.github.wesleyosantos91.algafoodapi.domain.service;

import io.github.wesleyosantos91.algafoodapi.domain.entity.Cozinha;
import io.github.wesleyosantos91.algafoodapi.domain.exception.ResourceNotFoundException;
import io.github.wesleyosantos91.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.text.MessageFormat.format;

@Service
public class CozinhaService {

    private final CozinhaRepository repository;

    public CozinhaService(CozinhaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Cozinha save(Cozinha cozinha) {
        return repository.save(cozinha);
    }

    @Transactional
    public Cozinha update(Long id, Cozinha cozinha) {
        return repository.save(cozinha);
    }

    @Transactional
    public void delete(Long id) {

        Cozinha cozinha = findById(id);
        repository.delete(cozinha);
    }

    public List<Cozinha> findAll() {
        return repository.findAll();
    }

    public Cozinha findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Not found regitstry with code {0}", id)));
    }
}
