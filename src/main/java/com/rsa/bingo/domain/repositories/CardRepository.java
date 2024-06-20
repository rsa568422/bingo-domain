package com.rsa.bingo.domain.repositories;

import com.rsa.bingo.domain.models.Card;

import java.util.Optional;

public interface CardRepository {

    Optional<Card> findById(Integer id);

    Iterable<Card> findByPlayerId(Integer playerId);

    Card save(Card card);

    void delete(Integer id);
}
