package io.github.wesleyosantos91.algafoodapi.domain.repository;

import io.github.wesleyosantos91.algafoodapi.domain.entity.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {


    @Query("from Restaurante r join fetch r.cozinha")
    List<Restaurante> findAll();
}
