package com.zherikhov.planningpoker.boards.model;

public record RoomId(String value) {

    public RoomId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Room id must not be blank");
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
