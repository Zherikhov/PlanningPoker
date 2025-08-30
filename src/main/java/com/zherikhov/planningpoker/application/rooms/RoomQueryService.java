package com.zherikhov.planningpoker.application.rooms;

import com.zherikhov.planningpoker.domain.rooms.Room;

import java.util.List;

public interface RoomQueryService {
    List<Room> findAll();
}
