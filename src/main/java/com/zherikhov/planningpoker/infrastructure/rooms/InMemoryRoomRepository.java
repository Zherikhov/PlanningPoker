package com.zherikhov.planningpoker.infrastructure.rooms;

import com.zherikhov.planningpoker.domain.rooms.Room;
import com.zherikhov.planningpoker.domain.rooms.RoomId;
import com.zherikhov.planningpoker.domain.rooms.RoomRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Simple in-memory implementation used for tests and prototypes.
 */
@Repository
public class InMemoryRoomRepository implements RoomRepository {

    private final Map<String, Room> storage = new ConcurrentHashMap<>();

    @Override
    public Room save(Room room) {
        storage.put(room.id().value(), room);
        return room;
    }

    @Override
    public Optional<Room> findById(RoomId id) {
        return Optional.ofNullable(storage.get(id.value()));
    }

    @Override
    public List<Room> findAll() {
        return new ArrayList<>(storage.values());
    }
}

