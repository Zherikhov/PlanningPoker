package com.zherikhov.planningpoker.api.rooms;

import com.zherikhov.planningpoker.application.rooms.RoomService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public Page<RoomSummaryDto> getRooms(@RequestParam(defaultValue = "false") boolean mine,
                                         Pageable pageable) {
        return roomService.getRooms(mine, pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateRoomResponse createRoom(@Valid @RequestBody CreateRoomRequest request) {
        return roomService.createRoom(request);
    }
}

