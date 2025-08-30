package com.zherikhov.planningpoker.infrastructure.persistence.service;

import com.zherikhov.planningpoker.application.rooms.RoomQueryService;
import com.zherikhov.planningpoker.domain.rooms.Room;
import com.zherikhov.planningpoker.domain.rooms.RoomId;
import com.zherikhov.planningpoker.domain.rooms.RoomStatus;
import com.zherikhov.planningpoker.domain.rooms.RoomRepository;
import com.zherikhov.planningpoker.infrastructure.persistence.dao.RoomJpaRepository;
import com.zherikhov.planningpoker.infrastructure.persistence.entity.RoomEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService implements RoomRepository, RoomQueryService {
    private final RoomJpaRepository repository;

    public RoomService(RoomJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Room save(Room room) {
        RoomEntity entity = new RoomEntity(room.id().value(), room.name(), room.status().name());
        RoomEntity saved = repository.save(entity);
        return new Room(new RoomId(saved.getId()), saved.getName(), RoomStatus.valueOf(saved.getStatus()));
    }

    @Override
    public List<Room> findAll() {
        return repository.findAll().stream()
                .map(e -> new Room(new RoomId(e.getId()), e.getName(), RoomStatus.valueOf(e.getStatus())))
                .toList();
    }
}

