package io.github.wesleyosantos91.algafoodapi.domain.service;

import static java.text.MessageFormat.format;

import io.github.wesleyosantos91.algafoodapi.domain.entity.Estado;
import io.github.wesleyosantos91.algafoodapi.domain.exception.ResourceNotFoundException;
import io.github.wesleyosantos91.algafoodapi.domain.repository.EstadoRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstadoService {

    private final EstadoRepository repository;

    public EstadoService(EstadoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Estado save(Estado estado) {
        return saveAndUpdate(estado);
    }

    @Transactional
    public Estado update(Estado estado) {
        return saveAndUpdate(estado);
    }

    @Transactional
    public void delete(Long id) {

        final Estado estado = findById(id);
        repository.delete(estado);
    }

    public List<Estado> findAll() {
        return repository.findAll();
    }

    public Estado findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Not found {0} registry with code {1}", Estado.class.getSimpleName(), id)));
    }

    private Estado saveAndUpdate(Estado estado) {
        return repository.save(estado);
    }
}
