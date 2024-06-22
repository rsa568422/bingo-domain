package com.rsa.bingo.domain.services;

import com.rsa.bingo.domain.models.Player;
import com.rsa.bingo.domain.repositories.CardRepository;
import com.rsa.bingo.domain.repositories.CustomizationRepository;
import com.rsa.bingo.domain.repositories.PlayerRepository;

import java.util.Optional;

public final class PlayerService {

    private final PlayerRepository playerRepository;

    private final CardRepository cardRepository;

    private final CustomizationRepository customizationRepository;

    public PlayerService(PlayerRepository playerRepository,
                            CardRepository cardRepository,
                            CustomizationRepository customizationRepository) {
        this.playerRepository = playerRepository;
        this.cardRepository = cardRepository;
        this.customizationRepository = customizationRepository;
    }

    public Iterable<Player> findAll() {
        return playerRepository.findAll();
    }

    public Optional<Player> findById(Integer id) {
        return playerRepository.findById(id);
    }

    public Iterable<Player> findByName(String name) {
        return playerRepository.findByName(name);
    }

    public Player save(Player player) {
        return playerRepository.save(player);
    }

    public void delete(Integer id) {
        cardRepository.findByPlayerId(id).forEach(card -> {
            customizationRepository.delete(card.getId());
            cardRepository.delete(card.getId());
        });
        playerRepository.delete(id);
    }
}
