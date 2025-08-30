package com.zherikhov.planningpoker.infrastructure.persistence.service;

import com.zherikhov.planningpoker.application.rooms.RoomQueryService;
import com.zherikhov.planningpoker.domain.rooms.Room;
import com.zherikhov.planningpoker.domain.rooms.RoomId;
import com.zherikhov.planningpoker.domain.rooms.RoomStatus;
import com.zherikhov.planningpoker.infrastructure.persistence.dao.RoomJpaRepository;
import com.zherikhov.planningpoker.infrastructure.persistence.entity.RoomEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService implements RoomQueryService {
    private final RoomJpaRepository repository;

    public RoomService(RoomJpaRepository repository) {
        this.repository = repository;
    }

    public RoomEntity save(RoomEntity room) {
        return repository.save(room);
    }

    public Optional<RoomEntity> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Room> findAll() {
        return repository.findAll().stream()
                .map(e -> new Room(new RoomId(e.getId()), e.getName(), RoomStatus.valueOf(e.getStatus())))
                .toList();
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}

