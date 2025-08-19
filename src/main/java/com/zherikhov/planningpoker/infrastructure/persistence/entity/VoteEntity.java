package com.zherikhov.planningpoker.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "vote")
public class VoteEntity {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private PlanningTaskEntity task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_id", nullable = false)
    private RoomParticipantEntity participant;

    @Column(name = "value_text", nullable = false)
    private String valueText;

    @Column(name = "value_numeric")
    private Double valueNumeric;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    public VoteEntity() {
    }

}
