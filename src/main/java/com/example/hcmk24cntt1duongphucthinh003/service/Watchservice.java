package com.example.hcmk24cntt1duongphucthinh003.service;

import com.example.hcmk24cntt1duongphucthinh003.dto.WatchPatchRequest;
import com.example.hcmk24cntt1duongphucthinh003.dto.WatchRequest;
import com.example.hcmk24cntt1duongphucthinh003.dto.WatchResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WatchService {
    WatchResponse create(WatchRequest request);

    Page<WatchResponse> findAll(String modelName, String brand, Pageable pageable);

    WatchResponse findById(Long id);

    WatchResponse update(Long id, WatchRequest request);

    WatchResponse patch(Long id, WatchPatchRequest request);

    void delete(Long id);
}
