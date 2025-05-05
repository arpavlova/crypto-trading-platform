package com.cryptovirtual.cryptovirtualworld.model;

public class HasDoneTransactions {
    private int userId;
    
    public int getUserId() {
        return userId;
    }
    public HasDoneTransactions(int userId, int transactionId) {
        this.userId = userId;
        this.transactionId = transactionId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
    private int transactionId;
}
