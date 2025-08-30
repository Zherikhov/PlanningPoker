package com.zherikhov.planningpoker.infrastructure.persistence;

import com.zherikhov.planningpoker.domain.rooms.Room;
import com.zherikhov.planningpoker.domain.rooms.RoomId;
import com.zherikhov.planningpoker.domain.rooms.RoomRepository;
import com.zherikhov.planningpoker.domain.rooms.RoomStatus;
import com.zherikhov.planningpoker.infrastructure.persistence.dao.RoomJpaRepository;
import com.zherikhov.planningpoker.infrastructure.persistence.entity.RoomEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoomRepositoryImpl implements RoomRepository {

    private final RoomJpaRepository repository;

    public RoomRepositoryImpl(RoomJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Room save(Room room) {
        RoomEntity entity = new RoomEntity(room.id().value(), room.name(), room.status().name());
        RoomEntity saved = repository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Room> findById(RoomId id) {
        return repository.findById(id.value()).map(this::toDomain);
    }

    @Override
    public List<Room> findAll() {
        return repository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public void deleteById(RoomId id) {
        repository.deleteById(id.value());
    }

    private Room toDomain(RoomEntity entity) {
        return new Room(new RoomId(entity.getId()), entity.getName(), RoomStatus.valueOf(entity.getStatus()));
    }
}
