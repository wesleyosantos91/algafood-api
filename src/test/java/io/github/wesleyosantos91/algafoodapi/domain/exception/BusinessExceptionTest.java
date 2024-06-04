package io.github.wesleyosantos91.algafoodapi.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BusinessExceptionTest {

    @Test
    @DisplayName("deve lançar exceção com a mensagem 'Exceção ao executar o método'")
    void deveLancarExcecao() {

        String mensagemEsperada = "Exceção ao executar o método";

        Throwable excecao = assertThrows(BusinessException.class, () -> {
            throw new BusinessException(mensagemEsperada);
        });

        assertEquals(mensagemEsperada, excecao.getMessage());
    }

    @Test
    @DisplayName("deve lançar exceção com a mensagem 'Exceção ao executar o método'")
    void deveLancarExcecaoComDoisParametros() {

        String mensagemEsperada = "Exceção ao executar o método";

        Throwable excecao = assertThrows(BusinessException.class, () -> {
            throw new BusinessException(mensagemEsperada, new Exception());
        });

        assertEquals(mensagemEsperada, excecao.getMessage());
    }
}
