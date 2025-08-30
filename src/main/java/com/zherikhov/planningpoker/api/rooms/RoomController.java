package com.zherikhov.planningpoker.api.rooms;

import com.zherikhov.planningpoker.application.rooms.CreateRoomService;
import com.zherikhov.planningpoker.domain.rooms.Room;
import com.zherikhov.planningpoker.infrastructure.persistence.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@Validated
public class RoomController {

    private final CreateRoomService createRoomService;
    private final RoomService roomService;

    public RoomController(CreateRoomService createRoomService, RoomService roomService) {
        this.createRoomService = createRoomService;
        this.roomService = roomService;
    }

    @PostMapping
    public ResponseEntity<RoomDto> createRoom(@Valid @RequestBody RoomDto request) {
        Room room = createRoomService.createRoom(request.name());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new RoomDto(room.id().value(), room.name(), room.status().name()));
    }

    @GetMapping
    public ResponseEntity<List<RoomDto>> getRooms() {
        List<RoomDto> rooms = roomService.findAll().stream()
                .map(e -> new RoomDto(e.getId(), e.getName(), e.getStatus()))
                .toList();
        return ResponseEntity.ok(rooms);
    }
}
