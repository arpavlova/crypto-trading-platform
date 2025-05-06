package com.cryptovirtual.cryptovirtualworld.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cryptovirtual.cryptovirtualworld.dao.CryptoCoinDAO;
import com.cryptovirtual.cryptovirtualworld.dao.HasCoinsDAO;
import com.cryptovirtual.cryptovirtualworld.dao.HasDoneTransactionsDAO;
import com.cryptovirtual.cryptovirtualworld.dao.TransactionDAO;
import com.cryptovirtual.cryptovirtualworld.dao.UserDAO;

@Service
public class UserService {

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


    public String resetAccount(int userId) {

        //transactionDAO.deleteAllTransactionsByUser(userId); //the history should remain
        userDAO.updateBalanceById(userId, INITIAL_BALANCE);
        hasCoinsDAO.deleteUserCoins(userId);
        return "Account reset for user " + userDAO.getUsernameById(userId);
    }

    private static final double INITIAL_BALANCE = 1000.0;
}
