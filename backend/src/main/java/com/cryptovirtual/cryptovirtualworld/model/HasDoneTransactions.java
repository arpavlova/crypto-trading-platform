package com.cryptovirtual.cryptovirtualworld.model;

public class HasDoneTransactions {
    
    public int getUserId() {
        return userId;
    }
    public HasDoneTransactions(int userId, int transactionId) {
        setUserId(userId);
        setTransactionId(transactionId);
    }
    public void setUserId(int userId) {
        //to validate
        this.userId = userId;
    }
    public int getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(int transactionId) {
        //to validate
        this.transactionId = transactionId;
    }
    private int userId;
    private int transactionId;
}
