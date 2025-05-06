package com.cryptovirtual.cryptovirtualworld.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cryptovirtual.cryptovirtualworld.dao.HasCoinsDAO;
import com.cryptovirtual.cryptovirtualworld.dao.UserDAO;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

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
