package io.github.wesleyosantos91.algafoodapi.utils.fixture.cozinha;

import io.github.wesleyosantos91.algafoodapi.api.v1.response.CozinhaResponse;

public class CozinhaResponseTemplateLoader {

    public static CozinhaResponse templateCreatedCozinhaResponse() {
        return new CozinhaResponse(1L, "Brasileira");
    }
}
