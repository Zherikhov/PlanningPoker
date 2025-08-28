package com.zherikhov.planningpoker.boards.impl;

import com.zherikhov.planningpoker.boards.api.CreateRoomUseCase;
import com.zherikhov.planningpoker.boards.api.RoomService;
import com.zherikhov.planningpoker.boards.model.Room;
import com.zherikhov.planningpoker.boards.model.RoomId;
import com.zherikhov.planningpoker.boards.model.RoomStatus;
import com.zherikhov.planningpoker.boards.persistence.RoomEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
class CreateRoomService implements CreateRoomUseCase {

    private final RoomService roomService;

    CreateRoomService(RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public Room createRoom(String name) {
        String id = UUID.randomUUID().toString();
        RoomEntity entity = new RoomEntity(id, name, RoomStatus.OPEN.name());
        roomService.save(entity);
        return new Room(new RoomId(entity.getId()), entity.getName(), RoomStatus.valueOf(entity.getStatus()));
    }
}
