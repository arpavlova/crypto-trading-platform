package com.cryptovirtual.cryptovirtualworld.controller;

import com.cryptovirtual.cryptovirtualworld.dao.*;
import com.cryptovirtual.cryptovirtualworld.model.*;
// import com.cryptovirtual.cryptovirtualworld.request.TradeRequest;
// import com.cryptovirtual.cryptovirtualworld.service.TradeService;
import com.cryptovirtual.cryptovirtualworld.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private TransactionDAO transactionDAO;
    @Autowired
    private CryptoCoinDAO cryptoCoinDAO;
     @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userDAO.getUserById(id);
    }

    @PostMapping("/")
    public void insertUser(@RequestBody User user) {
        userDAO.insertUser(user);
    }

    @PutMapping("/")
    public void updateUser(@RequestBody User user) {
        userDAO.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userDAO.deleteUser(id);
    }

    
    @PostMapping("/{userId}/reset")
    public ResponseEntity<?> resetAccount(@PathVariable int userId) {
        String result = userService.resetAccount(userId);
        double updatedBalance = userDAO.getUserById(userId).getBalance();
        Map<String, Object> response = new HashMap<>();
        response.put("message", result);
        response.put("balance", updatedBalance);
        return ResponseEntity.ok(response);
    }



    // ------------------- Transactions -------------------

    @GetMapping("/{id}/transactions")
    public List<Transaction> getTransactionsByUser(@PathVariable int id) {
        return transactionDAO.getTransactionsByUserId(id);
    }

    @GetMapping("/{id}/transactions/range")
    public List<Transaction> getTransactionsByUserInPeriod(@PathVariable int id,
                                                           @RequestParam String start,
                                                           @RequestParam String end) {
        return userDAO.getTransactionsByUserInTimePeriod(id, LocalDate.parse(start), LocalDate.parse(end));
    }

    @GetMapping("/{id}/gain-loss")
    public BigDecimal getTotalGainsRatio(@PathVariable int id,
                                         @RequestParam String start,
                                         @RequestParam String end) {
        return userDAO.getTotalGainsRatio(id, LocalDate.parse(start), LocalDate.parse(end));
    }

    @GetMapping("/coins")
    public List<CryptoCoin> getAllCoins() {
        return cryptoCoinDAO.getAllCoins();
    }
}
