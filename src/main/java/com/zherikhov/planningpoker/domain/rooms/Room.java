package com.zherikhov.planningpoker.domain.rooms;

import java.util.Objects;

public class Room {

    private final RoomId id;
    private final String name;
    private final RoomStatus status;

    public Room(RoomId id, String name, RoomStatus status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public RoomId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public RoomStatus status() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return Objects.equals(id, room.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
