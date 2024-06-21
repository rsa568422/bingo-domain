package com.rsa.bingo.domain.services;

import com.rsa.bingo.domain.models.Card;
import com.rsa.bingo.domain.models.Customization;
import com.rsa.bingo.domain.repositories.CardRepository;
import com.rsa.bingo.domain.repositories.CustomizationRepository;
import com.rsa.bingo.domain.utils.Parser;

import java.util.Optional;

public abstract class CardService {

    private final CardRepository cardRepository;

    private final CustomizationRepository customizationRepository;

    protected CardService(CardRepository cardRepository, CustomizationRepository customizationRepository) {
        this.cardRepository = cardRepository;
        this.customizationRepository = customizationRepository;
    }

    public Optional<Card> findById(Integer id) {
        return cardRepository.findById(id);
    }

    public Iterable<Card> findByPlayerId(Integer playerId) {
        return cardRepository.findByPlayerId(playerId);
    }

    public Card save(Card card) {
        return cardRepository.save(card);
    }

    public void delete(Integer id) {
        customizationRepository.delete(id);
        cardRepository.delete(id);
    }

    public byte[] toBytes(Card card, Customization customization) {
        return Parser.getBytes(card, customization);
    }
}
