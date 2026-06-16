package com.example.hcmk24cntt1duongphucthinh003.repository;

import com.example.hcmk24cntt1duongphucthinh003.entity.Watch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WatchRepository extends JpaRepository<Watch, Long> {
    Optional<Watch> findByIdAndDeletedFalse(Long id);

    Page<Watch> findByDeletedFalseAndModelNameContainingIgnoreCaseAndBrandContainingIgnoreCase(
            String modelName,
            String brand,
            Pageable pageable
    );
}
