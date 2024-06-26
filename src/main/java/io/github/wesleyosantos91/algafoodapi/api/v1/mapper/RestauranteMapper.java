package io.github.wesleyosantos91.algafoodapi.api.v1.mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import io.github.wesleyosantos91.algafoodapi.api.v1.request.RestauranteRequest;
import io.github.wesleyosantos91.algafoodapi.api.v1.response.RestauranteResponse;
import io.github.wesleyosantos91.algafoodapi.domain.entity.Restaurante;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = IGNORE,
        nullValueCheckStrategy = ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RestauranteMapper {

    Restaurante toEntiy(RestauranteRequest restauranteRequest);

    Restaurante toEntiy(RestauranteRequest restauranteRequest, @MappingTarget Restaurante restaurante);

    RestauranteResponse toResponse(Restaurante restaurante);


    default List<RestauranteResponse> toListResponse(List<Restaurante> entities) {
        final List<RestauranteResponse> list = new ArrayList<>();
        entities.forEach(e -> list.add(toResponse(e)));
        return list;
    }

    default Page<RestauranteResponse> toPageResponse(final Page<Restaurante> pages) {
        final List<RestauranteResponse> list = toListResponse(pages.getContent());
        return new PageImpl<>(list, pages.getPageable(), pages.getTotalElements());
    }
}
