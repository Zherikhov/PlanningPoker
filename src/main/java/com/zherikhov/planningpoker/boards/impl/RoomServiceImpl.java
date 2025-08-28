package com.zherikhov.planningpoker.boards.impl;

import com.zherikhov.planningpoker.boards.api.RoomService;
import com.zherikhov.planningpoker.boards.persistence.RoomRepository;
import com.zherikhov.planningpoker.boards.persistence.RoomEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class RoomServiceImpl implements RoomService {
    private final RoomRepository repository;

    RoomServiceImpl(RoomRepository repository) {
        this.repository = repository;
    }

    @Override
    public RoomEntity save(RoomEntity room) {
        return repository.save(room);
    }

    @Override
    public Optional<RoomEntity> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<RoomEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}

