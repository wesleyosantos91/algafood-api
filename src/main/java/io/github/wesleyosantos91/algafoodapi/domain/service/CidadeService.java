package io.github.wesleyosantos91.algafoodapi.domain.service;

import io.github.wesleyosantos91.algafoodapi.domain.entity.Cidade;
import io.github.wesleyosantos91.algafoodapi.domain.entity.Estado;
import io.github.wesleyosantos91.algafoodapi.domain.exception.ResourceNotFoundException;
import io.github.wesleyosantos91.algafoodapi.domain.repository.CidadeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.text.MessageFormat.format;

@Service
public class CidadeService {

    private final CidadeRepository repository;
    private final EstadoService estadoService;

    public CidadeService(CidadeRepository repository, EstadoService estadoService) {
        this.repository = repository;
        this.estadoService = estadoService;
    }

    @Transactional
    public Cidade save(Cidade cidade) {

        Estado estado = estadoService.findById(cidade.getEstado().getId());
        cidade.setEstado(estado);
        return repository.save(cidade);
    }

    @Transactional
    public Cidade update(Long id, Cidade cidade) {
        return repository.save(cidade);
    }

    @Transactional
    public void delete(Long id) {

        Cidade cidade = findById(id);
        repository.delete(cidade);
    }

    public List<Cidade> findAll() {
        return repository.findAll();
    }

    public Cidade findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Not found regitstry with code {0}", id)));
    }
}
