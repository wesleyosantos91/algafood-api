package io.github.wesleyosantos91.algafoodapi.utils.fixture.cozinha;

import io.github.wesleyosantos91.algafoodapi.domain.entity.Cozinha;
import java.util.List;

public class CozinhaTemplateLoader {

    public static Cozinha templateCreatedCozinhaRequest() {
        Cozinha cozinha = new Cozinha();
        cozinha.setId(1L);
        cozinha.setNome("Brasileira");
        cozinha.setRestaurantes(List.of());
        return cozinha;
    }

    public static Cozinha templateCreateNewCozinhaRequest() {
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Brasileira");
        cozinha.setRestaurantes(List.of());
        return cozinha;
    }
}

