package com.example.hcmk24cntt1duongphucthinh003.dto;

import com.example.hcmk24cntt1duongphucthinh003.entity.MovementType;
import com.example.hcmk24cntt1duongphucthinh003.entity.WatchStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class WatchRequest {
    @NotBlank(message = "model_name khong duoc de trong")
    @JsonProperty("model_name")
    private String modelName;

    @NotBlank(message = "brand khong duoc de trong")
    private String brand;

    @NotNull(message = "price khong duoc de trong")
    @Positive(message = "price phai lon hon 0")
    private BigDecimal price;

    @NotNull(message = "movement_type khong duoc de trong")
    @JsonProperty("movement_type")
    private MovementType movementType;

    @NotNull(message = "status khong duoc de trong")
    private WatchStatus status;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }

    public WatchStatus getStatus() {
        return status;
    }

    public void setStatus(WatchStatus status) {
        this.status = status;
    }
}
