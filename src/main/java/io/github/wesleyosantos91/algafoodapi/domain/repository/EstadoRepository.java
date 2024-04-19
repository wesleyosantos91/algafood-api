package io.github.wesleyosantos91.algafoodapi.domain.repository;

import io.github.wesleyosantos91.algafoodapi.domain.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
}
