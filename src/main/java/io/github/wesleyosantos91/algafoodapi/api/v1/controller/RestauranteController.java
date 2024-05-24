package io.github.wesleyosantos91.algafoodapi.api.v1.controller;

import io.github.wesleyosantos91.algafoodapi.api.v1.mapper.RestauranteMapper;
import io.github.wesleyosantos91.algafoodapi.api.v1.request.RestauranteRequest;
import io.github.wesleyosantos91.algafoodapi.api.v1.response.RestauranteResponse;
import io.github.wesleyosantos91.algafoodapi.domain.entity.Restaurante;
import io.github.wesleyosantos91.algafoodapi.domain.service.RestauranteService;
import jakarta.validation.Valid;
import java.util.List;
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
@RequestMapping("/restaurantes")
public record RestauranteController(RestauranteService service, RestauranteMapper mapper) {

    @GetMapping
    public ResponseEntity<List<RestauranteResponse>> list() {

        final List<Restaurante> restaurantes = service.findAll();
        return ResponseEntity.ok(mapper.toListResponse(restaurantes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestauranteResponse> findById(@PathVariable Long id) {

        final Restaurante restauranteSaved = service.findById(id);
        return ResponseEntity.ok(mapper.toResponse(restauranteSaved));
    }

    @PostMapping
    public ResponseEntity<RestauranteResponse> save(@RequestBody @Valid RestauranteRequest restauranteRequest) {

        final Restaurante entiy = mapper.toEntiy(restauranteRequest);
        final Restaurante restauranteSaved = service.save(entiy);
        return ResponseEntity.ok(mapper.toResponse(restauranteSaved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestauranteResponse> update(@PathVariable Long id, @RequestBody @Valid RestauranteRequest restauranteRequest) {

        final Restaurante restauranteSaved = service.findById(id);
        final Restaurante restauranteUpdated = service.update(mapper.toEntiy(restauranteRequest, restauranteSaved));
        return ResponseEntity.ok(mapper.toResponse(restauranteUpdated));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
