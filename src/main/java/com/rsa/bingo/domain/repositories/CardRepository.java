package com.rsa.bingo.domain.repositories;

import com.rsa.bingo.domain.models.Card;

import java.util.Optional;

public interface CardRepository {

    Iterable<Card> findAll();

    Optional<Card> findById(Integer id);

    Iterable<Card> findByUserId(Integer id);
}
