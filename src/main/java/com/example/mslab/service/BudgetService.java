package com.example.mslab.service;

import com.example.mslab.model.Budget;
import com.example.mslab.model.User;
import com.example.mslab.exception.UserNotFoundException;
import com.example.mslab.repository.BudgetRepository;
import com.example.mslab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private UserRepository userRepository;


    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }
    public Budget addBudget(Long userId, Budget budget) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
        budget.setUser(user);
        return budgetRepository.save(budget);
    }

    public List<Budget> getBudgetsByUserId(Long userId) {
        return budgetRepository.findByUserId(userId);
    }

    public Budget updateBudget(Long budgetId, Budget updatedBudget) {
        Budget existingBudget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new RuntimeException("Budget not found with ID: " + budgetId));
        existingBudget.setTitle(updatedBudget.getTitle());
        existingBudget.setAmount(updatedBudget.getAmount());
        return budgetRepository.save(existingBudget);
    }

}
