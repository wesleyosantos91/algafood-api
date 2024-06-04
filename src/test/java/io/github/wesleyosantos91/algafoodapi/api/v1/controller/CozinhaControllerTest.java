package io.github.wesleyosantos91.algafoodapi.api.v1.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.github.wesleyosantos91.algafoodapi.api.v1.mapper.CozinhaMapper;
import io.github.wesleyosantos91.algafoodapi.api.v1.request.CozinhaRequest;
import io.github.wesleyosantos91.algafoodapi.domain.entity.Cozinha;
import io.github.wesleyosantos91.algafoodapi.domain.service.CozinhaService;
import io.github.wesleyosantos91.algafoodapi.utils.fixture.cozinha.CozinhaResponseTemplateLoader;
import io.github.wesleyosantos91.algafoodapi.utils.fixture.cozinha.CozinhaTemplateLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@WebMvcTest(CozinhaController.class)
@ExtendWith(SpringExtension.class)
class CozinhaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CozinhaService service;

    @MockBean
    CozinhaMapper mapper;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Deve criar uma nova cozinha e retornar o ID com valor 5")
    void deveCriarNovaCozinhaERetornarIdComValor5() throws Exception {

        String json = """
                        {
                            "nome": "Brasileira"
                        }
                       """;

        when(mapper.toEntiy(any(CozinhaRequest.class))).thenReturn(CozinhaTemplateLoader.templateCreateNewCozinhaRequest());
        when(service.save(any(Cozinha.class))).thenReturn(CozinhaTemplateLoader.templateCreatedCozinhaRequest());
        when(mapper.toResponse(any(Cozinha.class))).thenReturn(CozinhaResponseTemplateLoader.templateCreatedCozinhaResponse());

        ResultActions result = mockMvc.perform(post("/cozinhas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));


        result.andDo(MockMvcResultHandlers.print());
        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.id").exists());
    }

    @Test
    @DisplayName("Deve criar uma nova cozinha e retornar o ID com valor 5")
    void deveRetornarBadRequestSeNomeForNull() throws Exception {

        String json = """
                        {
                       
                        }
                       """;

        ResultActions result = mockMvc.perform(post("/cozinhas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));


        result.andDo(MockMvcResultHandlers.print());
        result.andExpect(status().isBadRequest());
        result.andExpect(jsonPath("$.errors").exists());
        result.andExpect(jsonPath("$.errors[0].field").value("nome"));
        result.andExpect(jsonPath("$.errors[0].message_error").value("Nome da Cozinha é obrigatório"));
    }
}