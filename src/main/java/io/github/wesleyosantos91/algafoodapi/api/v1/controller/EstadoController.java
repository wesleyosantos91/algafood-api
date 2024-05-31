package io.github.wesleyosantos91.algafoodapi.api.v1.controller;

import io.github.wesleyosantos91.algafoodapi.api.v1.mapper.EstadoMapper;
import io.github.wesleyosantos91.algafoodapi.api.v1.request.EstadoRequest;
import io.github.wesleyosantos91.algafoodapi.api.v1.response.EstadoResponse;
import io.github.wesleyosantos91.algafoodapi.domain.entity.Estado;
import io.github.wesleyosantos91.algafoodapi.domain.service.EstadoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estados")
public record EstadoController(EstadoService service, EstadoMapper mapper) {

    @GetMapping
    public ResponseEntity<List<EstadoResponse>> list() {

        final List<Estado> estados = service.findAll();
        return ResponseEntity.ok(mapper.toListResponse(estados));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoResponse> findById(@PathVariable Long id) {

        final Estado estadoSaved = service.findById(id);
        return ResponseEntity.ok(mapper.toResponse(estadoSaved));
    }

    @PostMapping
    public ResponseEntity<EstadoResponse> save(@RequestBody @Valid EstadoRequest estadoRequest) {

        final Estado entiy = mapper.toEntiy(estadoRequest);
        final Estado estadoSaved = service.save(entiy);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(estadoSaved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoResponse> update(@PathVariable Long id, @RequestBody @Valid EstadoRequest estadoRequest) {

        final Estado estadoSaved = service.findById(id);
        final Estado estadoUpdated = service.update(mapper.toEntiy(estadoRequest, estadoSaved));
        return ResponseEntity.ok(mapper.toResponse(estadoUpdated));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
