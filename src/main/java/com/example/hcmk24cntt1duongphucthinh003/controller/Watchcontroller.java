package com.example.hcmk24cntt1duongphucthinh003.controller;

import com.example.hcmk24cntt1duongphucthinh003.dto.WatchPatchRequest;
import com.example.hcmk24cntt1duongphucthinh003.dto.WatchRequest;
import com.example.hcmk24cntt1duongphucthinh003.dto.WatchResponse;
import com.example.hcmk24cntt1duongphucthinh003.service.WatchService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/watches")
public class WatchController {
    private final WatchService watchService;

    public WatchController(WatchService watchService) {
        this.watchService = watchService;
    }

    @PostMapping
    public ResponseEntity<WatchResponse> create(@Valid @RequestBody WatchRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(watchService.create(request));
    }

    @GetMapping
    public Page<WatchResponse> findAll(
            @RequestParam(name = "model_name", required = false) String modelName,
            @RequestParam(required = false) String brand,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(
                Math.max(page, 0),
                Math.min(Math.max(size, 1), 100),
                Sort.by(Sort.Direction.ASC, "id")
        );
        return watchService.findAll(modelName, brand, pageable);
    }

    @GetMapping("/{id}")
    public WatchResponse findById(@PathVariable Long id) {
        return watchService.findById(id);
    }

    @PutMapping("/{id}")
    public WatchResponse update(@PathVariable Long id, @Valid @RequestBody WatchRequest request) {
        return watchService.update(id, request);
    }

    @PatchMapping("/{id}")
    public WatchResponse patch(@PathVariable Long id, @Valid @RequestBody WatchPatchRequest request) {
        return watchService.patch(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        watchService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
