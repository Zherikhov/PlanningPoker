package com.zherikhov.planningpoker.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomJpaRepository extends JpaRepository<RoomEntity, String> {
}
