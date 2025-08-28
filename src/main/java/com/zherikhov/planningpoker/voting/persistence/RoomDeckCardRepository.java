package com.zherikhov.planningpoker.voting.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zherikhov.planningpoker.voting.persistence.RoomDeckCardEntity;

public interface RoomDeckCardRepository extends JpaRepository<RoomDeckCardEntity, String> {
}

