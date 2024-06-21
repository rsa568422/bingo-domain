package com.rsa.bingo.domain.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@EqualsAndHashCode
public final class Customization {

    @Setter
    private Integer cardId;

    private final Color primary;

    private final Color secondary;

    public Customization(Color primary, Color secondary) {
        this.primary = primary;
        this.secondary = secondary;
    }

    public Customization(Integer cardId, Color primary, Color secondary) {
        this(primary, secondary);
        this.cardId = cardId;
    }
}
