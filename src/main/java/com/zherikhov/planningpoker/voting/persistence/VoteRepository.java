package com.zherikhov.planningpoker.voting.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zherikhov.planningpoker.voting.persistence.VoteEntity;

public interface VoteRepository extends JpaRepository<VoteEntity, String> {
}

