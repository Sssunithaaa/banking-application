package com.example.mslab.service;

import com.example.mslab.model.SpendingLimit;
import com.example.mslab.model.User;
import com.example.mslab.repository.SpendingLimitRepository;
import com.example.mslab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpendingLimitService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SpendingLimitRepository spendingLimitRepository;
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

    public boolean isLimitExceeded(Long userId, Double transactionAmount) {
        SpendingLimit spendingLimit = spendingLimitRepository.findByUserId(userId);
        return spendingLimit != null && transactionAmount > spendingLimit.getLimitAmount();
    }

}
