package io.github.wesleyosantos91.algafoodapi.api.v1.mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import io.github.wesleyosantos91.algafoodapi.api.v1.request.EstadoRequest;
import io.github.wesleyosantos91.algafoodapi.api.v1.response.EstadoResponse;
import io.github.wesleyosantos91.algafoodapi.domain.entity.Estado;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = IGNORE,
        nullValueCheckStrategy = ALWAYS
)
public interface EstadoMapper {

    Estado toEntiy(EstadoRequest estadoRequest);

    Estado toEntiy(EstadoRequest estadoRequest, @MappingTarget Estado estado);

    EstadoResponse toResponse(Estado estado);


    default List<EstadoResponse> toListResponse(final List<Estado> entities) {
        final List<EstadoResponse> list = new ArrayList<>();
        entities.forEach(e -> list.add(toResponse(e)));
        return list;
    }

    default Page<EstadoResponse> toPageResponse(final Page<Estado> pages) {
        final List<EstadoResponse> list = toListResponse(pages.getContent());
        return new PageImpl<>(list, pages.getPageable(), pages.getTotalElements());
    }
}
