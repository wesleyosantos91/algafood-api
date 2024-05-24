package io.github.wesleyosantos91.algafoodapi.api.v1.mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import io.github.wesleyosantos91.algafoodapi.api.v1.request.CozinhaRequest;
import io.github.wesleyosantos91.algafoodapi.api.v1.response.CozinhaResponse;
import io.github.wesleyosantos91.algafoodapi.domain.entity.Cozinha;
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
public interface CozinhaMapper {

    Cozinha toEntiy(CozinhaRequest cozinhaRequest);

    Cozinha toEntiy(CozinhaRequest cozinhaRequest, @MappingTarget Cozinha cozinha);

    CozinhaResponse toResponse(Cozinha cozinha);


    default List<CozinhaResponse> toListResponse(final List<Cozinha> entities) {
        final List<CozinhaResponse> list = new ArrayList<>();
        entities.forEach(e -> list.add(toResponse(e)));
        return list;
    }

    default Page<CozinhaResponse> toPageResponse(final Page<Cozinha> pages) {
        final List<CozinhaResponse> list = toListResponse(pages.getContent());
        return new PageImpl<>(list, pages.getPageable(), pages.getTotalElements());
    }
}
