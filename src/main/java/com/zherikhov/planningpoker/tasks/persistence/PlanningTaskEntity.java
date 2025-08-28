package com.zherikhov.planningpoker.tasks.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import com.zherikhov.planningpoker.boards.persistence.RoomEntity;

@Getter
@Setter
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

}
