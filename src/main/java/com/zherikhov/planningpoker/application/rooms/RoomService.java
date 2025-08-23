package com.zherikhov.planningpoker.application.rooms;

import com.zherikhov.planningpoker.api.rooms.CreateRoomRequest;
import com.zherikhov.planningpoker.api.rooms.CreateRoomResponse;
import com.zherikhov.planningpoker.api.rooms.RoomSummaryDto;
import com.zherikhov.planningpoker.domain.rooms.Room;
import com.zherikhov.planningpoker.domain.rooms.RoomId;
import com.zherikhov.planningpoker.domain.rooms.RoomRepository;
import com.zherikhov.planningpoker.domain.rooms.RoomStatus;
import com.zherikhov.planningpoker.domain.users.User;
import com.zherikhov.planningpoker.domain.votes.DeckType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public CreateRoomResponse createRoom(CreateRoomRequest request) {
        Room room = new Room(
                new RoomId(UUID.randomUUID().toString()),
                request.name(),
                request.description(),
                // stubbed owner
                new User("u1", "Owner"),
                0,
                Instant.now(),
                generateCode(),
                request.deckType() != null ? request.deckType() : DeckType.FIBONACCI,
                request.allowGuests(),
                RoomStatus.OPEN);
        roomRepository.save(room);
        return new CreateRoomResponse(room.id().value(), room.code());
    }

    public Page<RoomSummaryDto> getRooms(boolean mine, Pageable pageable) {
        List<Room> all = roomRepository.findAll();
        // ignore 'mine' filter for now; would filter by owner
        all.sort(Comparator.comparing(Room::updatedAt).reversed());
        int page = pageable.getPageNumber();
        int size = pageable.getPageSize();
        int start = Math.min(page * size, all.size());
        int end = Math.min(start + size, all.size());
        List<RoomSummaryDto> content = all.subList(start, end).stream()
                .map(RoomService::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(content, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updatedAt")), all.size());
    }

    private static RoomSummaryDto toDto(Room room) {
        return new RoomSummaryDto(
                room.id().value(),
                room.name(),
                room.description(),
                new RoomSummaryDto.Owner(room.owner().getId(), room.owner().getUsername()),
                room.tasksCount(),
                room.updatedAt(),
                room.code());
    }

    private String generateCode() {
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }
}

