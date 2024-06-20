package com.rsa.bingo.domain.services;

import com.rsa.bingo.domain.models.Colors;

import java.util.Optional;

public interface ColorsService {

    Iterable<Colors> findAll();

    Optional<Colors> findById(Integer id);

    Iterable<Colors> findByCardId(Integer cardId);
}
