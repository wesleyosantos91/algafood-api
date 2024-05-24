package io.github.wesleyosantos91.algafoodapi.api.v1.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.wesleyosantos91.algafoodapi.core.validation.Groups;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.groups.ConvertGroup;
import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record RestauranteRequest(
        @NotBlank
        String nome,
        @NotNull
        @PositiveOrZero
        BigDecimal taxaFrete,
        @Valid
        @ConvertGroup(to = Groups.CozinhaId.class)
        @NotNull
        CozinhaRequest cozinha) {

}
