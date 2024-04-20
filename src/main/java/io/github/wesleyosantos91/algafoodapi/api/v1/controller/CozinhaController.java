package io.github.wesleyosantos91.algafoodapi.api.v1.controller;

import io.github.wesleyosantos91.algafoodapi.api.v1.mapper.CozinhaMapper;
import io.github.wesleyosantos91.algafoodapi.api.v1.request.CozinhaRequest;
import io.github.wesleyosantos91.algafoodapi.api.v1.response.CozinhaResponse;
import io.github.wesleyosantos91.algafoodapi.domain.entity.Cozinha;
import io.github.wesleyosantos91.algafoodapi.domain.service.CozinhaService;
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
@RequestMapping("/cozinhas")
public record CozinhaController(CozinhaService service, CozinhaMapper mapper) {

    @GetMapping
    public ResponseEntity<List<CozinhaResponse>> list() {

        List<Cozinha> cozinhas = service.findAll();
        return ResponseEntity.ok(mapper.toListResponse(cozinhas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CozinhaResponse> findById(@PathVariable Long id) {

        Cozinha cozinhaSaved = service.findById(id);
        return ResponseEntity.ok(mapper.toResponse(cozinhaSaved));
    }

    @PostMapping
    public ResponseEntity<CozinhaResponse> save(@RequestBody @Valid CozinhaRequest request) {

        Cozinha entiy = mapper.toEntiy(request);
        Cozinha cozinhaSaved = service.save(entiy);
        return ResponseEntity.ok(mapper.toResponse(cozinhaSaved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CozinhaResponse> update(@PathVariable Long id, @RequestBody @Valid CozinhaRequest request) {

        Cozinha cozinhaSaved = service.findById(id);
        Cozinha cozinhaUpdated = service.update(id, mapper.toEntiy(request, cozinhaSaved));
        return ResponseEntity.ok(mapper.toResponse(cozinhaUpdated));
    }

    @DeleteMapping(value ="/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}