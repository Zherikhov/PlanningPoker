package com.zherikhov.planningpoker.infrastructure.persistence.dao;

import com.zherikhov.planningpoker.infrastructure.persistence.entity.RoomDeckCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomDeckCardJpaRepository extends JpaRepository<RoomDeckCardEntity, String> {
}

