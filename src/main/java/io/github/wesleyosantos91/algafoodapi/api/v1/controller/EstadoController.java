package io.github.wesleyosantos91.algafoodapi.api.v1.controller;

import io.github.wesleyosantos91.algafoodapi.api.v1.mapper.EstadoMapper;
import io.github.wesleyosantos91.algafoodapi.api.v1.request.EstadoRequest;
import io.github.wesleyosantos91.algafoodapi.api.v1.response.EstadoResponse;
import io.github.wesleyosantos91.algafoodapi.domain.entity.Estado;
import io.github.wesleyosantos91.algafoodapi.domain.service.EstadoService;
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
@RequestMapping("/estados")
public record EstadoController(EstadoService service, EstadoMapper mapper) {

    @GetMapping
    public ResponseEntity<List<EstadoResponse>> list() {

        List<Estado> estados = service.findAll();
        return ResponseEntity.ok(mapper.toListResponse(estados));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoResponse> findById(@PathVariable Long id) {

        Estado estadoSaved = service.findById(id);
        return ResponseEntity.ok(mapper.toResponse(estadoSaved));
    }

    @PostMapping
    public ResponseEntity<EstadoResponse> save(@RequestBody @Valid EstadoRequest request) {

        Estado entiy = mapper.toEntiy(request);
        Estado estadoSaved = service.save(entiy);
        return ResponseEntity.ok(mapper.toResponse(estadoSaved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoResponse> update(@PathVariable Long id, @RequestBody @Valid EstadoRequest request) {

        Estado estadoSaved = service.findById(id);
        Estado estadoUpdated = service.update(id, mapper.toEntiy(request, estadoSaved));
        return ResponseEntity.ok(mapper.toResponse(estadoUpdated));
    }

    @DeleteMapping(value ="/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}