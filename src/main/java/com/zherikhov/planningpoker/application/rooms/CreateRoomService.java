package com.zherikhov.planningpoker.application.rooms;

import com.zherikhov.planningpoker.domain.rooms.Room;
import com.zherikhov.planningpoker.domain.rooms.RoomId;
import com.zherikhov.planningpoker.domain.rooms.RoomStatus;
import com.zherikhov.planningpoker.infrastructure.persistence.entity.RoomEntity;
import com.zherikhov.planningpoker.infrastructure.persistence.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateRoomService {

    private final RoomService roomService;

    public CreateRoomService(RoomService roomService) {
        this.roomService = roomService;
    }

    public Room createRoom(String name) {
        String id = UUID.randomUUID().toString();
        RoomEntity entity = new RoomEntity(id, name, RoomStatus.OPEN.name());
        roomService.save(entity);
        return new Room(new RoomId(entity.getId()), entity.getName(), RoomStatus.valueOf(entity.getStatus()));
    }
}
