package com.zherikhov.planningpoker.application.rooms;

import com.zherikhov.planningpoker.domain.rooms.Room;
import com.zherikhov.planningpoker.domain.rooms.RoomId;
import com.zherikhov.planningpoker.domain.rooms.RoomRepository;
import com.zherikhov.planningpoker.domain.rooms.RoomStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateRoomService {

    private final RoomRepository roomRepository;

    public CreateRoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room createRoom(String name) {
        Room room = new Room(new RoomId(UUID.randomUUID().toString()), name, RoomStatus.OPEN);
        return roomRepository.save(room);
    }
}
