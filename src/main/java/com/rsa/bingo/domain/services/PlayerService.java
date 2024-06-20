package com.rsa.bingo.domain.services;

import com.rsa.bingo.domain.models.Player;

import java.util.Optional;

public interface PlayerService {

    Iterable<Player> findAll();

    Optional<Player> findById(Integer id);

    Iterable<Player> findByName(String name);

    Player save(Player player);

    void delete(Integer id);
}
