package io.github.wesleyosantos91.algafoodapi.api.v1.mapper;

import io.github.wesleyosantos91.algafoodapi.api.v1.request.CozinhaRequest;
import io.github.wesleyosantos91.algafoodapi.api.v1.response.CozinhaResponse;
import io.github.wesleyosantos91.algafoodapi.domain.entity.Cozinha;
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
public interface CozinhaMapper {

    Cozinha toEntiy(final CozinhaRequest cozinhaRequest);
    Cozinha toEntiy(final CozinhaRequest cozinhaRequest, @MappingTarget Cozinha cozinha);
    CozinhaResponse toResponse(final Cozinha cozinha);


    default List<CozinhaResponse> toListResponse(final List<Cozinha> entities){
        List<CozinhaResponse> list = new ArrayList<>();
        entities.forEach(e -> list.add(toResponse(e)));
        return list;
    }

    default Page<CozinhaResponse> toPageResponse(final Page<Cozinha> pages){
        List<CozinhaResponse> list = toListResponse(pages.getContent());
        return new PageImpl<>(list, pages.getPageable(), pages.getTotalElements());
    }
}
