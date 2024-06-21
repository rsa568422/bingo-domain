package com.rsa.bingo.domain.services;

import com.rsa.bingo.domain.models.Customization;
import com.rsa.bingo.domain.repositories.CustomizationRepository;

public abstract class CustomizationService {

    private final CustomizationRepository customizationRepository;

    protected CustomizationService(CustomizationRepository customizationRepository) {
        this.customizationRepository = customizationRepository;
    }

    public Iterable<Customization> findByCardId(Integer cardId) {
        return customizationRepository.findByCardId(cardId);
    }

    public Customization save(Customization customization) {
        return customizationRepository.save(customization);
    }

    public void delete(Integer cardId) {
        customizationRepository.delete(cardId);
    }

    public void delete(Customization customization) {
        customizationRepository.delete(customization);
    }
}
