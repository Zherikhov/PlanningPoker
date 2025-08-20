package com.zherikhov.planningpoker.infrastructure.persistence.dao;

import com.zherikhov.planningpoker.infrastructure.persistence.entity.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteJpaRepository extends JpaRepository<VoteEntity, String> {
}

