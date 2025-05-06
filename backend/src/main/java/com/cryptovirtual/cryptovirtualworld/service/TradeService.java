package com.cryptovirtual.cryptovirtualworld.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cryptovirtual.cryptovirtualworld.dao.UserDAO;
import com.cryptovirtual.cryptovirtualworld.dao.TransactionDAO;
import com.cryptovirtual.cryptovirtualworld.dao.CryptoCoinDAO;
import com.cryptovirtual.cryptovirtualworld.dao.HasCoinsDAO;
import com.cryptovirtual.cryptovirtualworld.dao.HasDoneTransactionsDAO;
import com.cryptovirtual.cryptovirtualworld.model.CryptoCoin;
import com.cryptovirtual.cryptovirtualworld.model.Transaction;
import com.cryptovirtual.cryptovirtualworld.model.User;
import com.cryptovirtual.cryptovirtualworld.request.TradeRequest;
import java.time.LocalDateTime;

// import org.springframework.http.ResponseEntity;
// import com.cryptovirtual.cryptovirtualworld.model.HasDoneTransactions;
// import java.math.BigDecimal;
// import java.util.HashMap;
// import java.util.Map;

@Service
public class TradeService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private TransactionDAO transactionDAO;

    @Autowired
    private HasDoneTransactionsDAO hasDonetransactionDAO;

    @Autowired
    private CryptoCoinDAO cryptoCoinDAO;

    @Autowired
    private HasCoinsDAO hasCoinsDAO;

    public String buy(int userId, TradeRequest request) {

        User user = userDAO.getUserById(userId);
        String symbol = request.getCryptoSymbol().toUpperCase(); //TO CHECK FOR NULL
        double amountToBuy = request.getAmount();

        CryptoCoin coin = cryptoCoinDAO.getCoinBySymbol(symbol);
        if (coin == null) { //would be null at all
            return "Crypto symbol not found.";
        }
        double price = coin.getPrice();
        double totalPrice = price * amountToBuy;


        double currentBalance = user.getBalance();
        if (totalPrice > currentBalance) {
            return "Insufficient balance.";
        }

        userDAO.updateBalanceById(userId, currentBalance - totalPrice);
        hasCoinsDAO.insertOrUpdate(userId, symbol, amountToBuy);
        Transaction transaction = new Transaction();
        transaction.setName(symbol);
        transaction.setType("Buy");
        transaction.setUserId(userId);
        transaction.setCryptoSymbol(symbol);
        transaction.setCryptoPrice(price);
        transaction.setAmount(amountToBuy);
        transaction.setDateOfTransaction(LocalDateTime.now());
        transactionDAO.save(transaction);
        //hasDonetransactionDAO.insert(userId, transaction.getId());
        return "Successfully bought " + amountToBuy + " of " + symbol;
    }


    public String sell(int userId, TradeRequest request) {

        User user = userDAO.getUserById(userId);
        String symbol = request.getCryptoSymbol().toUpperCase(); //TO CHECK FOR NULL
        double amountToSell = request.getAmount();

        CryptoCoin coin = cryptoCoinDAO.getCoinBySymbol(symbol);
        if (coin == null) { //would be null at all
            return "Crypto symbol not found.";
        }
        double price = coin.getPrice();

        double userCryptoAmount = hasCoinsDAO.getUserCoinAmount(userId, symbol);
        if (userCryptoAmount < amountToSell) {
             return "Unsuccessfully sold " + "your current ammount of " + symbol + " is " + userCryptoAmount;
        }
        double currentBalance = user.getBalance();
        double totalPrice = price * amountToSell;

        userDAO.updateBalanceById(userId, currentBalance + totalPrice);
        hasCoinsDAO.insertOrUpdate(userId, symbol, userCryptoAmount - amountToSell);

        Transaction transaction = new Transaction();
        transaction.setName(symbol);
        transaction.setType("Sell");
        transaction.setUserId(userId);
        transaction.setCryptoSymbol(symbol);
        transaction.setCryptoPrice(price);
        transaction.setAmount(amountToSell);
        transaction.setDateOfTransaction(LocalDateTime.now());
        transactionDAO.save(transaction);

        return "Successfully sold " + amountToSell + " of " + symbol;
    }

    public String deposit(int userId, double amount) {
        userDAO.updateBalanceById(userId, userDAO.getBalanceById(userId) + amount);
        Transaction transaction = new Transaction();
        transaction.setType("Deposit");
        transaction.setUserId(userId);
        transaction.setAmount(amount);
        transaction.setDateOfTransaction(LocalDateTime.now());
        transactionDAO.save(transaction);
        return "User " + userDAO.getUsernameById(userId) + " deposited " + amount;
    }
    
}
