package io.github.wesleyosantos91.algafoodapi.api.v1.controller;

import io.github.wesleyosantos91.algafoodapi.api.v1.mapper.CozinhaMapper;
import io.github.wesleyosantos91.algafoodapi.api.v1.request.CozinhaRequest;
import io.github.wesleyosantos91.algafoodapi.api.v1.response.CozinhaResponse;
import io.github.wesleyosantos91.algafoodapi.domain.entity.Cozinha;
import io.github.wesleyosantos91.algafoodapi.domain.service.CozinhaService;
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
@RequestMapping("/cozinhas")
public record CozinhaController(CozinhaService service, CozinhaMapper mapper) {

    @GetMapping
    public ResponseEntity<List<CozinhaResponse>> list() {

        final List<Cozinha> cozinhas = service.findAll();
        return ResponseEntity.ok(mapper.toListResponse(cozinhas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CozinhaResponse> findById(@PathVariable Long id) {

        final Cozinha cozinhaSaved = service.findById(id);
        return ResponseEntity.ok(mapper.toResponse(cozinhaSaved));
    }

    @PostMapping
    public ResponseEntity<CozinhaResponse> save(@RequestBody @Valid CozinhaRequest cozinhaRequest) {

        final Cozinha entiy = mapper.toEntiy(cozinhaRequest);
        final Cozinha cozinhaSaved = service.save(entiy);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(cozinhaSaved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CozinhaResponse> update(@PathVariable Long id, @RequestBody @Valid CozinhaRequest cozinhaRequest) {

        final Cozinha cozinhaSaved = service.findById(id);
        final Cozinha cozinhaUpdated = service.update(mapper.toEntiy(cozinhaRequest, cozinhaSaved));
        return ResponseEntity.ok(mapper.toResponse(cozinhaUpdated));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
