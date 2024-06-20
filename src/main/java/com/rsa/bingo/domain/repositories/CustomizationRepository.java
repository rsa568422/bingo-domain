package com.rsa.bingo.domain.repositories;

import com.rsa.bingo.domain.models.Customization;

public interface CustomizationRepository {

    Customization save(Customization customization);

    void delete(Integer cardId);

    void delete(Integer cardId, Integer colorsId);
}
