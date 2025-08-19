package com.zherikhov.planningpoker.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "room_deck_card")
public class RoomDeckCardEntity {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private RoomEntity room;

    @Column(nullable = false)
    private String label;

    @Column(name = "numeric_value")
    private BigDecimal numericValue;

    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder;

    public RoomDeckCardEntity() {
    }

}
