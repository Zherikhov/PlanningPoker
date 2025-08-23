package com.zherikhov.planningpoker.domain.rooms;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    Room save(Room room);
    Optional<Room> findById(RoomId id);
    List<Room> findAll();
}
