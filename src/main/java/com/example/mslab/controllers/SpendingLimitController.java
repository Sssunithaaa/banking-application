package com.example.mslab.controllers;

import com.example.mslab.model.SpendingLimit;
import com.example.mslab.service.SpendingLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/spending-limits")
public class SpendingLimitController {

    @Autowired
    private SpendingLimitService spendingLimitService;

    @PostMapping("/{userId}")
    public ResponseEntity<SpendingLimit> setSpendingLimit(@PathVariable Long userId, @RequestBody Double limitAmount) {
        SpendingLimit spendingLimit = spendingLimitService.setSpendingLimit(userId, limitAmount);
        return ResponseEntity.ok(spendingLimit);
    }
}
