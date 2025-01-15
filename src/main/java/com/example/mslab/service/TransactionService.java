package com.example.mslab.service;

import com.example.mslab.model.Transaction;
import com.example.mslab.model.User;
import com.example.mslab.exception.UserNotFoundException;
import com.example.mslab.repository.TransactionRepository;
import com.example.mslab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll(); // Correct return type is List<Transaction>
    }
    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    public Transaction addTransaction(Long userId, Transaction transaction) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        // Adjust user balance based on transaction type
        if ("debit".equalsIgnoreCase(transaction.getType())) {
            if (user.getBalance() < transaction.getAmount()) {
                throw new IllegalArgumentException("Insufficient balance");
            }
            user.setBalance(user.getBalance() - transaction.getAmount());
        } else if ("credit".equalsIgnoreCase(transaction.getType())) {
            user.setBalance(user.getBalance() + transaction.getAmount());
        }

        userRepository.save(user);
        transaction.setUser(user);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsByUserId(Long userId) {
        return transactionRepository.findByUserId(userId);
    }

    public void deleteTransaction(Long transactionId) {
        if (!transactionRepository.existsById(transactionId)) {
            throw new RuntimeException("Transaction not found with ID: " + transactionId);
        }
        transactionRepository.deleteById(transactionId);
    }

    public Map<String, Double> getTransactionSummary(Long userId) {
        List<Transaction> transactions = transactionRepository.findByUserId(userId);
        double totalDebit = transactions.stream()
                .filter(t -> "debit".equalsIgnoreCase(t.getType()))
                .mapToDouble(Transaction::getAmount)
                .sum();
        double totalCredit = transactions.stream()
                .filter(t -> "credit".equalsIgnoreCase(t.getType()))
                .mapToDouble(Transaction::getAmount)
                .sum();

        Map<String, Double> summary = new HashMap<>();
        summary.put("totalDebit", totalDebit);
        summary.put("totalCredit", totalCredit);
        summary.put("currentBalance", totalCredit - totalDebit);
        return summary;
    }



    public List<Transaction> getTransactionsByDateRange(Long userId, Date startDate, Date endDate) {
        return transactionRepository.findByUserIdAndDateBetween(userId, startDate, endDate);
    }

    public String checkLowBalance(Long userId, Double threshold) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
        if (user.getBalance() < threshold) {
            return "Warning: Your balance is below the threshold of " + threshold;
        }
        return "Balance is sufficient.";
    }




}
