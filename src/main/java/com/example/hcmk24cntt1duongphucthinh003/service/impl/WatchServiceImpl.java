package com.example.hcmk24cntt1duongphucthinh003.service.impl;

import com.example.hcmk24cntt1duongphucthinh003.dto.WatchPatchRequest;
import com.example.hcmk24cntt1duongphucthinh003.dto.WatchRequest;
import com.example.hcmk24cntt1duongphucthinh003.dto.WatchResponse;
import com.example.hcmk24cntt1duongphucthinh003.entity.Watch;
import com.example.hcmk24cntt1duongphucthinh003.exception.NotFoundException;
import com.example.hcmk24cntt1duongphucthinh003.mapper.WatchMapper;
import com.example.hcmk24cntt1duongphucthinh003.repository.WatchRepository;
import com.example.hcmk24cntt1duongphucthinh003.service.WatchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WatchServiceImpl implements WatchService {
    private final WatchRepository watchRepository;
    private final WatchMapper watchMapper;

    public WatchServiceImpl(WatchRepository watchRepository, WatchMapper watchMapper) {
        this.watchRepository = watchRepository;
        this.watchMapper = watchMapper;
    }

    @Override
    public WatchResponse create(WatchRequest request) {
        Watch watch = watchRepository.save(watchMapper.toEntity(request));
        return watchMapper.toResponse(watch);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WatchResponse> findAll(String modelName, String brand, Pageable pageable) {
        String modelNameFilter = modelName == null ? "" : modelName.trim();
        String brandFilter = brand == null ? "" : brand.trim();

        return watchRepository
                .findByDeletedFalseAndModelNameContainingIgnoreCaseAndBrandContainingIgnoreCase(
                        modelNameFilter,
                        brandFilter,
                        pageable
                )
                .map(watchMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public WatchResponse findById(Long id) {
        return watchMapper.toResponse(findActiveWatch(id));
    }

    @Override
    public WatchResponse update(Long id, WatchRequest request) {
        Watch watch = findActiveWatch(id);
        watchMapper.copyToEntity(request, watch);
        return watchMapper.toResponse(watchRepository.save(watch));
    }

    @Override
    public WatchResponse patch(Long id, WatchPatchRequest request) {
        validatePatchRequest(request);

        Watch watch = findActiveWatch(id);
        if (request.getModelName() != null) {
            watch.setModelName(request.getModelName().trim());
        }
        if (request.getBrand() != null) {
            watch.setBrand(request.getBrand().trim());
        }
        if (request.getPrice() != null) {
            watch.setPrice(request.getPrice());
        }
        if (request.getMovementType() != null) {
            watch.setMovementType(request.getMovementType());
        }
        if (request.getStatus() != null) {
            watch.setStatus(request.getStatus());
        }

        return watchMapper.toResponse(watchRepository.save(watch));
    }

    @Override
    public void delete(Long id) {
        Watch watch = findActiveWatch(id);
        watch.setDeleted(true);
        watchRepository.save(watch);
    }

    private Watch findActiveWatch(Long id) {
        return watchRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("Khong tim thay Watch voi id = " + id));
    }

    private void validatePatchRequest(WatchPatchRequest request) {
        boolean hasAnyField = request.getModelName() != null
                || request.getBrand() != null
                || request.getPrice() != null
                || request.getMovementType() != null
                || request.getStatus() != null;

        if (!hasAnyField) {
            throw new IllegalArgumentException("Can cung cap it nhat mot truong de cap nhat");
        }
        if (request.getModelName() != null && request.getModelName().trim().isEmpty()) {
            throw new IllegalArgumentException("model_name khong duoc de trong");
        }
        if (request.getBrand() != null && request.getBrand().trim().isEmpty()) {
            throw new IllegalArgumentException("brand khong duoc de trong");
        }
    }
}
