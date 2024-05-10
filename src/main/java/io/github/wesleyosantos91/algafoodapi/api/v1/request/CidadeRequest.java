package io.github.wesleyosantos91.algafoodapi.api.v1.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.wesleyosantos91.algafoodapi.core.validation.Groups;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record CidadeRequest(
        String nome,
        @Valid
        @ConvertGroup(to = Groups.EstadoId.class)
        @NotNull
        EstadoRequest estado
) {
}
