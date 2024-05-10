package io.github.wesleyosantos91.algafoodapi.api.v1.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.wesleyosantos91.algafoodapi.core.validation.Groups;
import jakarta.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record EstadoRequest(
        @NotNull(groups = Groups.EstadoId.class)
        Long id,
        String nome
) {
}
