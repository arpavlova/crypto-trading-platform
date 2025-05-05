package com.cryptovirtual.cryptovirtualworld.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cryptovirtual.cryptovirtualworld.model.Transaction;
import com.cryptovirtual.cryptovirtualworld.service.TradeService;

@RestController
@RequestMapping("/api")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @PostMapping("/buy")
    public ResponseEntity<?> buy(@RequestBody TradeRequest req) {
        return tradeService.buy(req);
    }

    @PostMapping("/sell")
    public ResponseEntity<?> sell(@RequestBody TradeRequest req) {
        return tradeService.sell(req);
    }

    @GetMapping("/balance/{userId}")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable int userId) {
        return ResponseEntity.ok(tradeService.getBalance(userId));
    }

    @GetMapping("/transactions/{userId}")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable int userId) {
        return ResponseEntity.ok(tradeService.getTransactions(userId));
    }

    @PostMapping("/reset/{userId}")
    public ResponseEntity<?> reset(@PathVariable int userId) {
        return tradeService.resetAccount(userId);
    }
}
