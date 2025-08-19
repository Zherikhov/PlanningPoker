package com.zherikhov.planningpoker.infrastructure.persistence.jpa;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "planning_task")
public class PlanningTaskEntity {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private RoomEntity room;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "text")
    private String description;

    @Column(nullable = false)
    private String status;

    @Column(name = "final_value")
    private String finalValue;

    @Column(name = "final_numeric")
    private Double finalNumeric;

    @Column(name = "estimated_at")
    private OffsetDateTime estimatedAt;

    @Column(name = "external_system")
    private String externalSystem;

    @Column(name = "external_key")
    private String externalKey;

    @Column(name = "external_url")
    private String externalUrl;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    public PlanningTaskEntity() {
    }

    // getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFinalValue() {
        return finalValue;
    }

    public void setFinalValue(String finalValue) {
        this.finalValue = finalValue;
    }

    public Double getFinalNumeric() {
        return finalNumeric;
    }

    public void setFinalNumeric(Double finalNumeric) {
        this.finalNumeric = finalNumeric;
    }

    public OffsetDateTime getEstimatedAt() {
        return estimatedAt;
    }

    public void setEstimatedAt(OffsetDateTime estimatedAt) {
        this.estimatedAt = estimatedAt;
    }

    public String getExternalSystem() {
        return externalSystem;
    }

    public void setExternalSystem(String externalSystem) {
        this.externalSystem = externalSystem;
    }

    public String getExternalKey() {
        return externalKey;
    }

    public void setExternalKey(String externalKey) {
        this.externalKey = externalKey;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
