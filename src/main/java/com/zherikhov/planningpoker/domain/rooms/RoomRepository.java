package com.zherikhov.planningpoker.domain.rooms;

import java.util.List;

public interface RoomRepository {
    Room save(Room room);
    List<Room> findAll();
}
