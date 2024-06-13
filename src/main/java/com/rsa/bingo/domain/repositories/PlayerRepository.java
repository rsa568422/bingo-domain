package com.rsa.bingo.domain.repositories;

import com.rsa.bingo.domain.models.Player;

import java.util.Optional;

public interface PlayerRepository {

    Iterable<Player> findAll();

    Optional<Player> findById(Integer id);

    Optional<Player> findByName(String name);

    Player save(Player player);

    void delete(Integer id);
}
