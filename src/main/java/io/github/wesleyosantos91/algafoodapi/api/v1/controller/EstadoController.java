package io.github.wesleyosantos91.algafoodapi.api.v1.controller;

import io.github.wesleyosantos91.algafoodapi.domain.entity.Cozinha;
import io.github.wesleyosantos91.algafoodapi.domain.entity.Estado;
import io.github.wesleyosantos91.algafoodapi.domain.repository.CozinhaRepository;
import io.github.wesleyosantos91.algafoodapi.domain.repository.EstadoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estados")
public record EstadoController(EstadoRepository repository) {

    @GetMapping
    public List<Estado> list() {
        return repository.findAll();
    }
}
