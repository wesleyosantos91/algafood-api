package io.github.wesleyosantos91.algafoodapi.domain.service;

import io.github.wesleyosantos91.algafoodapi.domain.entity.Estado;
import io.github.wesleyosantos91.algafoodapi.domain.exception.ResourceNotFoundException;
import io.github.wesleyosantos91.algafoodapi.domain.repository.EstadoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.text.MessageFormat.format;

@Service
public class EstadoService {

    private final EstadoRepository repository;

    public EstadoService(EstadoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Estado save(Estado cozinha) {
        return repository.save(cozinha);
    }

    @Transactional
    public Estado update(Long id, Estado cozinha) {
        return repository.save(cozinha);
    }

    @Transactional
    public void delete(Long id) {

        Estado cozinha = findById(id);
        repository.delete(cozinha);
    }

    public List<Estado> findAll() {
        return repository.findAll();
    }

    public Estado findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Not found regitstry with code {0}", id)));
    }
}
