package com.zherikhov.planningpoker.infrastructure.persistence.dao;

import com.zherikhov.planningpoker.infrastructure.persistence.entity.RoomParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomParticipantJpaRepository extends JpaRepository<RoomParticipantEntity, String> {
}

