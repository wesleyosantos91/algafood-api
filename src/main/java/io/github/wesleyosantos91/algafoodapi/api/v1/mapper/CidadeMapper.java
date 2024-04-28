package io.github.wesleyosantos91.algafoodapi.api.v1.mapper;

import io.github.wesleyosantos91.algafoodapi.api.v1.request.CidadeRequest;
import io.github.wesleyosantos91.algafoodapi.api.v1.response.CidadeResponse;
import io.github.wesleyosantos91.algafoodapi.domain.entity.Cidade;
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
public interface CidadeMapper {

    Cidade toEntiy(final CidadeRequest cidadeRequest);
    Cidade toEntiy(final CidadeRequest cidadeRequest, @MappingTarget Cidade cidade);
    CidadeResponse toResponse(final Cidade cidade);


    default List<CidadeResponse> toListResponse(final List<Cidade> entities){
        List<CidadeResponse> list = new ArrayList<>();
        entities.forEach(e -> list.add(toResponse(e)));
        return list;
    }

    default Page<CidadeResponse> toPageResponse(final Page<Cidade> pages){
        List<CidadeResponse> list = toListResponse(pages.getContent());
        return new PageImpl<>(list, pages.getPageable(), pages.getTotalElements());
    }
}