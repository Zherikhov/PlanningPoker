package com.zherikhov.planningpoker.infrastructure.persistence.service;

import com.zherikhov.planningpoker.domain.rooms.Room;
import com.zherikhov.planningpoker.domain.rooms.RoomId;
import com.zherikhov.planningpoker.domain.rooms.RoomRepository;
import com.zherikhov.planningpoker.domain.rooms.RoomStatus;
import com.zherikhov.planningpoker.infrastructure.persistence.dao.RoomJpaRepository;
import com.zherikhov.planningpoker.infrastructure.persistence.entity.RoomEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SpringDataRepos implements RoomRepository {

    private final RoomJpaRepository roomJpaRepository;

    public SpringDataRepos(RoomJpaRepository roomJpaRepository) {
        this.roomJpaRepository = roomJpaRepository;
    }

    @Override
    public Room save(Room room) {
        RoomEntity entity = new RoomEntity(room.id().value(), room.name(), room.status().name());
        roomJpaRepository.save(entity);
        return room;
    }

    @Override
    public Optional<Room> findById(RoomId id) {
        return roomJpaRepository.findById(id.toString())
                .map(e -> new Room(new RoomId(e.getId()), e.getName(), RoomStatus.valueOf(e.getStatus())));
    }
}
