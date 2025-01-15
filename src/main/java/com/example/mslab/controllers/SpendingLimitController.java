package com.example.mslab.controllers;

import com.example.mslab.model.SpendingLimit;
import com.example.mslab.service.SpendingLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spending-limits")
public class SpendingLimitController {

    @Autowired
    private SpendingLimitService spendingLimitService;

    // Get all spending limits
    @GetMapping
    public List<SpendingLimit> getAllSpendingLimits() {
        return spendingLimitService.getAllSpendingLimits(); // Fetches all limits
    }

    // Get spending limit for a user
    @GetMapping("/{userId}")
    public ResponseEntity<SpendingLimit> getSpendingLimit(@PathVariable Long userId) {
        SpendingLimit spendingLimit = spendingLimitService.getSpendingLimit(userId);
        return spendingLimit != null ?
                ResponseEntity.ok(spendingLimit) :
                ResponseEntity.notFound().build();
    }

    // Set a spending limit for a user
    @PostMapping("/{userId}")
    public ResponseEntity<SpendingLimit> setSpendingLimit(@PathVariable Long userId, @RequestBody Double limitAmount) {
        SpendingLimit spendingLimit = spendingLimitService.setSpendingLimit(userId, limitAmount);
        return ResponseEntity.ok(spendingLimit);
    }

    // Update spending limit for a user
    @PutMapping("/{userId}")
    public ResponseEntity<SpendingLimit> updateSpendingLimit(@PathVariable Long userId, @RequestBody Double limitAmount) {
        System.out.println(userId);
        SpendingLimit spendingLimit = spendingLimitService.updateSpendingLimit(userId, limitAmount);
        return ResponseEntity.ok(spendingLimit);
    }

    // Delete spending limit for a user
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteSpendingLimit(@PathVariable Long userId) {
        spendingLimitService.deleteSpendingLimit(userId);
        return ResponseEntity.noContent().build();
    }

    // Check if spending limit is exceeded
    @GetMapping("/check-limit/{userId}/{transactionAmount}")
    public ResponseEntity<Boolean> isLimitExceeded(@PathVariable Long userId, @PathVariable Double transactionAmount) {
        boolean isExceeded = spendingLimitService.isLimitExceeded(userId, transactionAmount);
        return ResponseEntity.ok(isExceeded);
    }
}
