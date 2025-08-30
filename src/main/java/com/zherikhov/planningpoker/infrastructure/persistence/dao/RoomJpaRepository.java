package com.zherikhov.planningpoker.infrastructure.persistence.dao;

import com.zherikhov.planningpoker.infrastructure.persistence.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomJpaRepository extends JpaRepository<RoomEntity, String> {
}
