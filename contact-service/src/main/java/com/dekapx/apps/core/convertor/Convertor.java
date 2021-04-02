package com.dekapx.apps.core.convertor;

public interface Convertor<E, D> {
    E toEntity(D dto);

    D toDto(E entity);
}
