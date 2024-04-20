package io.github.wesleyosantos91.algafoodapi.api.v1.controller;

import io.github.wesleyosantos91.algafoodapi.api.v1.mapper.CidadeMapper;
import io.github.wesleyosantos91.algafoodapi.api.v1.request.CidadeRequest;
import io.github.wesleyosantos91.algafoodapi.api.v1.response.CidadeResponse;
import io.github.wesleyosantos91.algafoodapi.domain.entity.Cidade;
import io.github.wesleyosantos91.algafoodapi.domain.service.CidadeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public record CidadeController(CidadeService service, CidadeMapper mapper) {

    @GetMapping
    public ResponseEntity<List<CidadeResponse>> list() {

        List<Cidade> cidades = service.findAll();
        return ResponseEntity.ok(mapper.toListResponse(cidades));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CidadeResponse> findById(@PathVariable Long id) {

        Cidade cidadeSaved = service.findById(id);
        return ResponseEntity.ok(mapper.toResponse(cidadeSaved));
    }

    @PostMapping
    public ResponseEntity<CidadeResponse> save(@RequestBody @Valid CidadeRequest request) {

        Cidade entiy = mapper.toEntiy(request);
        Cidade cidadeSaved = service.save(entiy);
        return ResponseEntity.ok(mapper.toResponse(cidadeSaved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CidadeResponse> update(@PathVariable Long id, @RequestBody @Valid CidadeRequest request) {

        Cidade cidadeSaved = service.findById(id);
        Cidade cidadeUpdated = service.update(id, mapper.toEntiy(request, cidadeSaved));
        return ResponseEntity.ok(mapper.toResponse(cidadeUpdated));
    }

    @DeleteMapping(value ="/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}