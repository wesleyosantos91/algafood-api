package io.github.wesleyosantos91.algafoodapi.api.v1.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import io.github.wesleyosantos91.algafoodapi.api.v1.request.CozinhaRequest;
import io.github.wesleyosantos91.algafoodapi.api.v1.response.CozinhaResponse;
import io.github.wesleyosantos91.algafoodapi.utils.config.ContainerBaseConfig;
import io.github.wesleyosantos91.algafoodapi.utils.fixture.cozinha.CozinhaRequestTemplateLoader;
import java.util.Objects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CozinhaControllerIT extends ContainerBaseConfig {

    @LocalServerPort
    Integer port;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    @DisplayName("Deve criar uma nova cozinha e retornar o ID com valor 5")
    void deveCriarNovaCozinhaERetornarIdComValor5() {

        CozinhaRequest cozinhaRequest = CozinhaRequestTemplateLoader.templateCreateNewCozinhaRequest();
        ResponseEntity<CozinhaResponse> response =
                restTemplate.postForEntity("http://localhost:" + port + "/cozinhas", cozinhaRequest, CozinhaResponse.class);

        assertThat(response).isNotNull().satisfies(r -> {
            assertThat(r.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
            assertThat(Objects.requireNonNull(r.getBody()).id()).isEqualTo(5L);
        });
    }
}
