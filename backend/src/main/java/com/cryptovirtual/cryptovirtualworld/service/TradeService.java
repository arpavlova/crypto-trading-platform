package com.cryptovirtual.cryptovirtualworld.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cryptovirtual.cryptovirtualworld.dao.UserDAO;
import com.cryptovirtual.cryptovirtualworld.dao.TransactionDAO;
import com.cryptovirtual.cryptovirtualworld.dao.CryptoCoinDAO;
import com.cryptovirtual.cryptovirtualworld.dao.HasCoinsDAO;
import com.cryptovirtual.cryptovirtualworld.model.CryptoCoin;
import com.cryptovirtual.cryptovirtualworld.model.Transaction;
import com.cryptovirtual.cryptovirtualworld.model.User;
import com.cryptovirtual.cryptovirtualworld.request.TradeRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TradeService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private TransactionDAO transactionDAO;

    @Autowired
    private CryptoCoinDAO cryptoCoinDAO;

    @Autowired
    private HasCoinsDAO hasCoinsDAO;

    // public String resetAccount(int userId) {
    //     transactionDAO.deleteAllTransactionsByUser(userId);
    //     userDAO.resetBalance(userId);
    //     return "Account reset for user " + userId;
    // }

    public String buy(int userId, TradeRequest request) {

        User user = userDAO.getUserById(userId);

        String symbol = request.getCryptoSymbol().toUpperCase();
        double amountToBuy = request.getAmount();

        // 1. Validate symbol
        CryptoCoin coin = cryptoCoinDAO.getCoinBySymbol(symbol);
        if (coin == null) {
            return "Crypto symbol not found.";
        }

        double price = coin.getPrice();
        double totalPrice = price * amountToBuy;

        // 2. Validate user balance
        double currentBalance = user.getBalance();
        if (totalPrice > currentBalance) {
            return "Insufficient balance.";
        }

        // 3. Update balance
        userDAO.updateBalanceById(userId, currentBalance - totalPrice);

        // 4. Update coin holdings
        hasCoinsDAO.insertOrUpdate(userId, symbol, amountToBuy);

        // 5. Record transaction
        Transaction transaction = new Transaction();
        transaction.setName("Buy " + symbol);
        transaction.setType("Buy");
        transaction.setUserId(userId);
        transaction.setCryptoSymbol(symbol);
        transaction.setCryptoPrice(price);
        transaction.setAmount(amountToBuy);
        transaction.setDateOfTransaction(LocalDateTime.now());

        transactionDAO.save(transaction);

        return "Successfully bought " + amountToBuy + " of " + symbol;
    }
}
