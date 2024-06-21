package com.rsa.bingo.domain.repositories;

import com.rsa.bingo.domain.models.Customization;

public interface CustomizationRepository {

    Iterable<Customization> findByCardId(Integer cardId);

    Customization save(Customization customization);

    void delete(Integer cardId);

    void delete(Customization customization);
}
