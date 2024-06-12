package com.rsa.bingo;

import com.rsa.bingo.domain.models.Card;

public final class Data {

    public static Integer[][] VALUES() {
        return new Integer[][] {
                {null, 18, 24, null, null, 54, 67, null, 80},
                {null, null, 26, null, 41, 55, null, 70, 87},
                {4, null, null, 39, 42, null, 69, 72, null}
        };
    }

    public static Card CARD() {
        return new Card(VALUES());
    }
}
