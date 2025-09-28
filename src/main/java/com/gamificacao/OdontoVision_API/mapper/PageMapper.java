package com.gamificacao.OdontoVision_API.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import java.util.function.Function;

public interface PageMapper {

    default <E, D> Page<D> map(Page<E> page, Function<E, D> mapper) {
        return new PageImpl<>(
                page.map(mapper).getContent(),
                page.getPageable(),
                page.getTotalElements()
        );
    }
}
