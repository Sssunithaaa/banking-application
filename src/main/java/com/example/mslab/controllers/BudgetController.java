package com.example.mslab.controllers;

import com.example.mslab.model.Budget;
import com.example.mslab.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;


    @GetMapping
    public List<Budget> getAllBudgets() {
        return budgetService.getAllBudgets();
    }
    @PostMapping("/{userId}")
    public ResponseEntity<Budget> addBudget(@PathVariable Long userId, @RequestBody Budget budget) {
        Budget newBudget = budgetService.addBudget(userId, budget);
        return ResponseEntity.ok(newBudget);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Budget>> getBudgetsByUserId(@PathVariable Long userId) {
        List<Budget> budgets = budgetService.getBudgetsByUserId(userId);
        return ResponseEntity.ok(budgets);
    }

    @PutMapping("/{budgetId}")
    public ResponseEntity<Budget> updateBudget(@PathVariable Long budgetId, @RequestBody Budget updatedBudget) {
        Budget budget = budgetService.updateBudget(budgetId, updatedBudget);
        return ResponseEntity.ok(budget);
    }



}
