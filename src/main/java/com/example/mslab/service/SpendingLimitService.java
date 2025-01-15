package com.example.mslab.service;

import com.example.mslab.model.SpendingLimit;
import com.example.mslab.model.User;
import com.example.mslab.repository.SpendingLimitRepository;
import com.example.mslab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpendingLimitService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SpendingLimitRepository spendingLimitRepository;

    // Set or update spending limit for a user
    public SpendingLimit setSpendingLimit(Long userId, Double limitAmount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        SpendingLimit spendingLimit = spendingLimitRepository.findByUserId(userId);
        if (spendingLimit == null) {
            spendingLimit = new SpendingLimit();
            spendingLimit.setUser(user);
        }
        spendingLimit.setLimitAmount(limitAmount);
        return spendingLimitRepository.save(spendingLimit);
    }

    // Update spending limit for a user
    public SpendingLimit updateSpendingLimit(Long userId, Double limitAmount) {
        SpendingLimit spendingLimit = spendingLimitRepository.findByUserId(userId);
        if (spendingLimit == null) {
            throw new RuntimeException("Spending limit not found for User ID: " + userId);
        }
        spendingLimit.setLimitAmount(limitAmount);
        return spendingLimitRepository.save(spendingLimit);
    }

    // Delete spending limit for a user
    public void deleteSpendingLimit(Long userId) {
        SpendingLimit spendingLimit = spendingLimitRepository.findByUserId(userId);
        if (spendingLimit == null) {
            throw new RuntimeException("Spending limit not found for User ID: " + userId);
        }
        spendingLimitRepository.delete(spendingLimit);
    }

    // Retrieve spending limit for a user
    public SpendingLimit getSpendingLimit(Long userId) {
        return spendingLimitRepository.findByUserId(userId);
    }

    // Retrieve all spending limits
    public List<SpendingLimit> getAllSpendingLimits() {
        return spendingLimitRepository.findAll();
    }

    // Check if the spending limit is exceeded for a user
    public boolean isLimitExceeded(Long userId, Double transactionAmount) {
        SpendingLimit spendingLimit = spendingLimitRepository.findByUserId(userId);
        return spendingLimit != null && transactionAmount > spendingLimit.getLimitAmount();
    }
}
