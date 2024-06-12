package com.rsa.bingo.domain.repositories;

import com.rsa.bingo.domain.models.Colors;

import java.util.Optional;

public interface ColorsRepository {

    Optional<Colors> findById(Integer id);

    Iterable<Colors> findByUserId(Integer userId);
}
