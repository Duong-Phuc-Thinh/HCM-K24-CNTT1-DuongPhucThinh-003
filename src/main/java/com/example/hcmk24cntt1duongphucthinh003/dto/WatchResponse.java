package com.example.hcmk24cntt1duongphucthinh003.dto;

import com.example.hcmk24cntt1duongphucthinh003.entity.MovementType;
import com.example.hcmk24cntt1duongphucthinh003.entity.WatchStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class WatchResponse {
    private Long id;

    @JsonProperty("model_name")
    private String modelName;

    private String brand;

    private BigDecimal price;

    @JsonProperty("movement_type")
    private MovementType movementType;

    private WatchStatus status;

    @JsonProperty("is_deleted")
    private boolean deleted;

    public WatchResponse(Long id, String modelName, String brand, BigDecimal price,
                         MovementType movementType, WatchStatus status, boolean deleted) {
        this.id = id;
        this.modelName = modelName;
        this.brand = brand;
        this.price = price;
        this.movementType = movementType;
        this.status = status;
        this.deleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public String getModelName() {
        return modelName;
    }

    public String getBrand() {
        return brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public WatchStatus getStatus() {
        return status;
    }

    public boolean isDeleted() {
        return deleted;
    }
}
