package com.example.mslab.controllers;

import com.example.mslab.model.Transaction;
import com.example.mslab.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions(); // Returns a list of transactions
    }


    @GetMapping("/transaction-id/{id}")
    public Optional<Transaction> getTransactionById(@PathVariable("id") Long id) {
        return transactionService.getTransactionById(id);
    }
    @PostMapping("/{userId}")
    public ResponseEntity<Transaction> addTransaction(@PathVariable Long userId, @RequestBody Transaction transaction) {
        Transaction newTransaction = transactionService.addTransaction(userId, transaction);
        return ResponseEntity.ok(newTransaction);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Transaction>> getTransactionsByUserId(@PathVariable Long userId) {
        List<Transaction> transactions = transactionService.getTransactionsByUserId(userId);
        return ResponseEntity.ok(transactions);
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Long transactionId) {
        transactionService.deleteTransaction(transactionId);
        return ResponseEntity.ok("Transaction deleted successfully.");
    }

    @GetMapping("/{userId}/summary")
    public ResponseEntity<Map<String, Double>> getTransactionSummary(@PathVariable Long userId) {
        Map<String, Double> summary = transactionService.getTransactionSummary(userId);
        return ResponseEntity.ok(summary);
    }

    @GetMapping("/{userId}/date-range")
    public ResponseEntity<List<Transaction>> getTransactionsByDateRange(
            @PathVariable Long userId,
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<Transaction> transactions = transactionService.getTransactionsByDateRange(userId, startDate, endDate);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{userId}/low-balance")
    public ResponseEntity<String> checkLowBalance(@PathVariable Long userId, @RequestParam("threshold") Double threshold) {
        String message = transactionService.checkLowBalance(userId, threshold);
        return ResponseEntity.ok(message);
    }




}
