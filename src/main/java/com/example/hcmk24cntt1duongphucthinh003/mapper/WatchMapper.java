package com.example.hcmk24cntt1duongphucthinh003.mapper;

import com.example.hcmk24cntt1duongphucthinh003.dto.WatchRequest;
import com.example.hcmk24cntt1duongphucthinh003.dto.WatchResponse;
import com.example.hcmk24cntt1duongphucthinh003.entity.Watch;
import org.springframework.stereotype.Component;

@Component
public class WatchMapper {
    public Watch toEntity(WatchRequest request) {
        return new Watch(
                request.getModelName().trim(),
                request.getBrand().trim(),
                request.getPrice(),
                request.getMovementType(),
                request.getStatus()
        );
    }

    public WatchResponse toResponse(Watch watch) {
        return new WatchResponse(
                watch.getId(),
                watch.getModelName(),
                watch.getBrand(),
                watch.getPrice(),
                watch.getMovementType(),
                watch.getStatus(),
                watch.isDeleted()
        );
    }

    public void copyToEntity(WatchRequest request, Watch watch) {
        watch.setModelName(request.getModelName().trim());
        watch.setBrand(request.getBrand().trim());
        watch.setPrice(request.getPrice());
        watch.setMovementType(request.getMovementType());
        watch.setStatus(request.getStatus());
    }
}
