package com.example.mslab.repository;

import com.example.mslab.model.SpendingLimit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpendingLimitRepository extends JpaRepository<SpendingLimit, Long> {
    SpendingLimit findByUserId(Long userId);
}
