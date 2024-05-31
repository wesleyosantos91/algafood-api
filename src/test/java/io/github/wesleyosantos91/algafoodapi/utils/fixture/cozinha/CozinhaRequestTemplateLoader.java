package io.github.wesleyosantos91.algafoodapi.utils.fixture.cozinha;

import io.github.wesleyosantos91.algafoodapi.api.v1.request.CozinhaRequest;

public class CozinhaRequestTemplateLoader {

    public static CozinhaRequest templateCreateNewCozinhaRequest() {
        return new CozinhaRequest(null, "Brasileira");
    }
}
