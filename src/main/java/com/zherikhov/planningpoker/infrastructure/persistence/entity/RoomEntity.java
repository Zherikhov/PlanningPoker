package com.zherikhov.planningpoker.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rooms")
public class RoomEntity {

    @Id
    private String id;

    private String name;

    private String status;

    public RoomEntity() {
    }

    public RoomEntity(String id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

}
