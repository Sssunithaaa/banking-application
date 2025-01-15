package com.example.mslab.controllers;
import com.example.mslab.model.Transaction;
import com.example.mslab.model.User;
import com.example.mslab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }
    @GetMapping("/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.findUserByEmail(email);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User user = userService.updateUser(id, updatedUser);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }



    @PostMapping("/{id}/transactions")
    public ResponseEntity<User> addTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        User user = userService.addTransaction(id, transaction);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<Transaction>> getAllTransactions(@PathVariable Long id) {
        List<Transaction> transactions = userService.getAllTransactions(id);
        return ResponseEntity.ok(transactions);
    }


    // Add more endpoints as needed
}
