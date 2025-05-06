package com.cryptovirtual.cryptovirtualworld.controller;

import com.cryptovirtual.cryptovirtualworld.dao.*;
import com.cryptovirtual.cryptovirtualworld.model.*;
import com.cryptovirtual.cryptovirtualworld.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    private UserService userService;
    
    @PostMapping("/{userId}/reset")
    public ResponseEntity<?> resetAccount(@PathVariable int userId) {
        String result = userService.resetAccount(userId);
        double updatedBalance = userDAO.getUserById(userId).getBalance();
        Map<String, Object> response = new HashMap<>();
        response.put("message", result);
        response.put("balance", updatedBalance);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}/transactions")
    public List<Transaction> getTransactionsByUser(@PathVariable int userId) {

        return userDAO.getAllTransactionsByUserId(userId);
    }

    @GetMapping("/{userId}/gains")
    public Double gains(@PathVariable int userId) {

        return userDAO.getTotalGains(userId);
    }

    // TODO
    // getTransactionsByUserInTimeInterval
    // getgainsByUserInTimeInterval
    // getAllCoins/holdings
    // getAllUsers
    // getUserById
    // insertUser;
    // userDAO.updateUser
    // deleteUser
}
