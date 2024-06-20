package com.rsa.bingo;

import com.rsa.bingo.domain.models.Card;
import com.rsa.bingo.domain.models.Colors;
import com.rsa.bingo.domain.models.Customization;
import com.rsa.bingo.domain.models.Player;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.util.List;

public final class Data {

    public static Integer[][] VALUES() {
        return new Integer[][] {
                {null, 18, 24, null, null, 54, 67, null, 80},
                {null, null, 26, null, 41, 55, null, 70, 87},
                {4, null, null, 39, 42, null, 69, 72, null}
        };
    }

    public static Integer[][] OTHER_VALUES() {
        return new Integer[][] {
                {null, 18, 24, null, null, 54, 67, null, 80},
                {null, null, 26, null, 41, 55, null, 70, 87},
                {5, null, null, 39, 42, null, 69, 72, null}
        };
    }

    public static Card CARD() {
        return new Card(VALUES());
    }

    public static Colors COLORS() {
        return new Colors(new int[] {255, 0, 0}, new int[] {255, 128, 0});
    }

    public static Player PLAYER() {
        return new Player(1, "Player");
    }

    public static Customization CUSTOMIZATION() {
        return new Customization(CARD(), COLORS());
    }

    public static List<Pair<Integer, Integer>> EMPTIES() {
        return List.of(
                Pair.of(0, 0), Pair.of(0, 3), Pair.of(0, 4), Pair.of(0, 7),
                Pair.of(1, 0), Pair.of(1, 1), Pair.of(1, 3), Pair.of(1, 6),
                Pair.of(2, 1), Pair.of(2, 2), Pair.of(2, 5), Pair.of(2, 8)
        );
    }

    public static List<Triple<Integer, Integer, Integer>> CARD_VALUES() {
        return List.of(
                Triple.of(0, 1, 18), Triple.of(0, 2, 24), Triple.of(0, 5, 54), Triple.of(0, 6, 67), Triple.of(0, 8, 80),
                Triple.of(1, 2, 26), Triple.of(1, 4, 41), Triple.of(1, 5, 55), Triple.of(1, 7, 70), Triple.of(1, 8, 87),
                Triple.of(2, 0, 4), Triple.of(2, 3, 39), Triple.of(2, 4, 42), Triple.of(2, 6, 69), Triple.of(2, 7, 72)
        );
    }
}
