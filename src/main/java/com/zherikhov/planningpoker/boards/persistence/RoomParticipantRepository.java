package com.zherikhov.planningpoker.boards.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomParticipantRepository extends JpaRepository<RoomParticipantEntity, String> {
}

