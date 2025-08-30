package com.zherikhov.planningpoker.domain.votes;

import java.util.List;

public class VoteDeck {
    private static final List<Integer> VALUES = List.of(1, 2, 3, 5, 8, 13, 21, 34);

    public List<Integer> values() {
        return VALUES;
    }
}
