package com.zherikhov.planningpoker.boards.api;

import com.zherikhov.planningpoker.boards.persistence.RoomEntity;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    RoomEntity save(RoomEntity room);
    Optional<RoomEntity> findById(String id);
    List<RoomEntity> findAll();
    void deleteById(String id);
}
