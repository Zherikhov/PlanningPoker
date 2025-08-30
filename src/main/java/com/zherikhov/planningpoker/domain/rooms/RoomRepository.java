package com.zherikhov.planningpoker.domain.rooms;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for accessing room aggregates.
 */
public interface RoomRepository {
    Room save(Room room);
    Optional<Room> findById(RoomId id);
    List<Room> findAll();
    void deleteById(RoomId id);
}
