package com.zherikhov.planningpoker.infrastructure.persistence.jpa;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PlanningTaskEntity getTask() {
        return task;
    }

    public void setTask(PlanningTaskEntity task) {
        this.task = task;
    }

    public RoomParticipantEntity getParticipant() {
        return participant;
    }

    public void setParticipant(RoomParticipantEntity participant) {
        this.participant = participant;
    }

    public String getValueText() {
        return valueText;
    }

    public void setValueText(String valueText) {
        this.valueText = valueText;
    }

    public Double getValueNumeric() {
        return valueNumeric;
    }

    public void setValueNumeric(Double valueNumeric) {
        this.valueNumeric = valueNumeric;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
