package com.rsa.bingo.domain.repositories;

import com.rsa.bingo.domain.models.Colors;

import java.util.Optional;

public interface ColorsRepository {

    Iterable<Colors> findAll();

    Optional<Colors> findById(Integer id);

    Iterable<Colors> findByCardId(Integer cardId);
}
