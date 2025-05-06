package com.cryptovirtual.cryptovirtualworld.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.cryptovirtual.cryptovirtualworld.dao.UserDAO;
import com.cryptovirtual.cryptovirtualworld.request.TradeRequest;
import com.cryptovirtual.cryptovirtualworld.service.TradeService;

@RestController
@RequestMapping("/api/trade")
@CrossOrigin(origins = "http://localhost:3000")
public class TradeController {

    @Autowired
    private TradeService tradeService;
    @Autowired
    private UserDAO userDAO;

    @PostMapping("/{userId}/buy")
     public ResponseEntity<Map<String, Object>> buy(@PathVariable int userId, @RequestBody TradeRequest req) {
        String result = tradeService.buy(userId, req);
        double updatedBalance = userDAO.getUserById(userId).getBalance();
        Map<String, Object> response = new HashMap<>();
        response.put("message", result);
        response.put("balance", updatedBalance);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{userId}/sell")
    public ResponseEntity<Map<String, Object>> sell(@PathVariable int userId, @RequestBody TradeRequest req) {
        String result = tradeService.sell(userId, req);
        double updatedBalance = userDAO.getUserById(userId).getBalance();
        Map<String, Object> response = new HashMap<>();
        response.put("message", result);
        response.put("balance", updatedBalance);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{userId}/deposit")
    public ResponseEntity<Map<String, Object>> deposit(@PathVariable int userId, @RequestBody TradeRequest req) {
        String result = tradeService.deposit(userId, req.getAmount());
        double updatedBalance = userDAO.getBalanceById(userId);
        Map<String, Object> response = new HashMap<>();
        response.put("message", result);
        response.put("balance", updatedBalance);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{userId}/withdraw")
    public ResponseEntity<Map<String, Object>> withdraw(@PathVariable int userId, @RequestBody TradeRequest req) {
        String result = tradeService.withdraw(userId, req.getAmount());
        double updatedBalance = userDAO.getBalanceById(userId);
        Map<String, Object> response = new HashMap<>();
        response.put("message", result);
        response.put("balance", updatedBalance);

        return ResponseEntity.ok(response);
    }

    //TODO:
    // getTransactionsByUser
    //...
}
