package io.github.wesleyosantos91.algafoodapi.api.v1.mapper;

import io.github.wesleyosantos91.algafoodapi.api.v1.request.RestauranteRequest;
import io.github.wesleyosantos91.algafoodapi.api.v1.response.RestauranteResponse;
import io.github.wesleyosantos91.algafoodapi.domain.entity.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = IGNORE,
        nullValueCheckStrategy = ALWAYS
)
public interface RestauranteMapper {

    Restaurante toEntiy(final RestauranteRequest restauranteRequest);
    Restaurante toEntiy(final RestauranteRequest restauranteRequest, @MappingTarget Restaurante restaurante);
    RestauranteResponse toResponse(final Restaurante restaurante);


    default List<RestauranteResponse> toListResponse(final List<Restaurante> entities){
        List<RestauranteResponse> list = new ArrayList<>();
        entities.forEach(e -> list.add(toResponse(e)));
        return list;
    }

    default Page<RestauranteResponse> toPageResponse(final Page<Restaurante> pages){
        List<RestauranteResponse> list = toListResponse(pages.getContent());
        return new PageImpl<>(list, pages.getPageable(), pages.getTotalElements());
    }
}
