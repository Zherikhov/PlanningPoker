package com.zherikhov.planningpoker.api.rooms;

import com.zherikhov.planningpoker.application.rooms.CreateRoomService;
import com.zherikhov.planningpoker.application.rooms.RoomQueryService;
import com.zherikhov.planningpoker.domain.rooms.Room;
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
    private final RoomQueryService roomQueryService;

    public RoomController(CreateRoomService createRoomService, RoomQueryService roomQueryService) {
        this.createRoomService = createRoomService;
        this.roomQueryService = roomQueryService;
    }

    @PostMapping
    public ResponseEntity<RoomDto> createRoom(@Valid @RequestBody RoomDto request) {
        Room room = createRoomService.createRoom(request.name(), request.description());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new RoomDto(room.id().value(), room.name(), room.description(), room.status().name()));
    }

    @GetMapping
    public ResponseEntity<List<RoomDto>> getRooms() {
        List<RoomDto> rooms = roomQueryService.findAll().stream()
                .map(e -> new RoomDto(e.id().value(), e.name(), e.description(), e.status().name()))
                .toList();
        return ResponseEntity.ok(rooms);
    }
}
