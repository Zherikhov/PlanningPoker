package com.zherikhov.planningpoker.voting.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import com.zherikhov.planningpoker.tasks.persistence.PlanningTaskEntity;
import com.zherikhov.planningpoker.boards.persistence.RoomParticipantEntity;

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
