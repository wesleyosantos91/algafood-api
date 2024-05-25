package io.github.wesleyosantos91.algafoodapi.domain.service;

import static java.text.MessageFormat.format;

import io.github.wesleyosantos91.algafoodapi.domain.entity.Cidade;
import io.github.wesleyosantos91.algafoodapi.domain.entity.Estado;
import io.github.wesleyosantos91.algafoodapi.domain.exception.BusinessException;
import io.github.wesleyosantos91.algafoodapi.domain.exception.ResourceNotFoundException;
import io.github.wesleyosantos91.algafoodapi.domain.repository.CidadeRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return saveAndUpdate(cidade);
    }

    public Cidade update(Cidade cidade) {
        return saveAndUpdate(cidade);
    }

    @Transactional
    public void delete(Long id) {

        final Cidade cidade = findById(id);
        repository.delete(cidade);
    }

    public List<Cidade> findAll() {
        return repository.findAll();
    }

    public Cidade findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Not found {0} registry with code {1}", Cidade.class.getSimpleName(), id)));
    }

    private Cidade saveAndUpdate(Cidade cidade) {
        try {
            final Estado estado = estadoService.findById(cidade.getEstado().getId());
            cidade.setEstado(estado);
            return repository.save(cidade);
        } catch (ResourceNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}
