package com.zherikhov.planningpoker.api.rooms;

import com.zherikhov.planningpoker.application.rooms.CreateRoomService;
import com.zherikhov.planningpoker.domain.rooms.Room;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final CreateRoomService createRoomService;

    public RoomController(CreateRoomService createRoomService) {
        this.createRoomService = createRoomService;
    }

    @PostMapping
    public ResponseEntity<RoomDto> createRoom(@RequestBody RoomDto request) {
        Room room = createRoomService.createRoom(request.name());
        return ResponseEntity.ok(new RoomDto(room.id().value(), room.name(), room.status().name()));
    }

    @GetMapping
    public ResponseEntity<List<RoomDto>> getRooms() {
        // In a real implementation, this would return all rooms from repository.
        return ResponseEntity.ok(List.of());
    }
}
