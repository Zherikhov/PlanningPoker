package com.zherikhov.planningpoker.events.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zherikhov.planningpoker.events.persistence.EventLogEntity;

public interface EventLogRepository extends JpaRepository<EventLogEntity, Long> {
}

