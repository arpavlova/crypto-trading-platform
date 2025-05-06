package com.cryptovirtual.cryptovirtualworld.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cryptovirtual.cryptovirtualworld.dao.TransactionDAO;
import com.cryptovirtual.cryptovirtualworld.model.Transaction;
// import java.math.BigDecimal;
// import java.time.LocalDate;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.http.ResponseEntity;
// import com.cryptovirtual.cryptovirtualworld.dao.CryptoCoinDAO;
// import com.cryptovirtual.cryptovirtualworld.dao.UserDAO;
// import com.cryptovirtual.cryptovirtualworld.service.TradeService;
// import com.cryptovirtual.cryptovirtualworld.request.TradeRequest;
import com.cryptovirtual.cryptovirtualworld.request.TradeRequest;
import com.cryptovirtual.cryptovirtualworld.service.TradeService;

@RestController
@RequestMapping("/api/trade")
@CrossOrigin(origins = "http://localhost:3000")
public class TradeController {

    @Autowired
    private TradeService tradeService;
    @Autowired
    private TransactionDAO transactionDAO;

    
    @GetMapping("/{Userid}/transactions")
    public List<Transaction> getTransactionsByUser(@PathVariable int id) {
        return transactionDAO.getTransactionsByUserId(id);
    }

    @PostMapping("/{userId}/buy")
     public /*ResponseEntity<?>*/ String buy(@RequestBody TradeRequest req) {
        return tradeService.buy(1, req);
    }

    // @PostMapping("/{userId}//sell")
    // public ResponseEntity<?> sell(@RequestBody TradeRequest req) {
    //     return tradeService.sell(req);
    // }

    // @GetMapping("/transactions/{userId}")
    // public ResponseEntity<List<Transaction>> getTransactions(@PathVariable int userId) {
    //     return ResponseEntity.ok(tradeService.getTransactions(userId));
    // }

    // @PostMapping("/reset/{userId}")
    // public ResponseEntity<?> reset(@PathVariable int userId) {
    //     return tradeService.resetAccount(userId);
    // }
}
