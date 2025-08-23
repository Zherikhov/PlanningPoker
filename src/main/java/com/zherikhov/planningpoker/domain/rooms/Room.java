package com.zherikhov.planningpoker.domain.rooms;

import com.zherikhov.planningpoker.domain.users.User;
import com.zherikhov.planningpoker.domain.votes.DeckType;

import java.time.Instant;
import java.util.Objects;

/**
 * Domain entity representing a planning room/board.
 */
public class Room {

    private final RoomId id;
    private final String name;
    private final String description;
    private final User owner;
    private final int tasksCount;
    private final Instant updatedAt;
    private final String code;
    private final DeckType deckType;
    private final boolean allowGuests;
    private final RoomStatus status;

    public Room(RoomId id,
                String name,
                String description,
                User owner,
                int tasksCount,
                Instant updatedAt,
                String code,
                DeckType deckType,
                boolean allowGuests,
                RoomStatus status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.tasksCount = tasksCount;
        this.updatedAt = updatedAt;
        this.code = code;
        this.deckType = deckType;
        this.allowGuests = allowGuests;
        this.status = status;
    }

    public RoomId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }

    public User owner() {
        return owner;
    }

    public int tasksCount() {
        return tasksCount;
    }

    public Instant updatedAt() {
        return updatedAt;
    }

    public String code() {
        return code;
    }

    public DeckType deckType() {
        return deckType;
    }

    public boolean allowGuests() {
        return allowGuests;
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
