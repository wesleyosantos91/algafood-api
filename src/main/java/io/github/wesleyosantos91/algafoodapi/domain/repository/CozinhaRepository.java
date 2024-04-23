package io.github.wesleyosantos91.algafoodapi.domain.repository;

import io.github.wesleyosantos91.algafoodapi.domain.entity.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

}
