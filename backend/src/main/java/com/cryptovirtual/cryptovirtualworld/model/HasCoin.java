package com.cryptovirtual.cryptovirtualworld.model;

public class HasCoin {

    public HasCoin(int userId, String cryptoSymbol, double amount) {
        setUserId(userId);
        setCryptoSymbol(cryptoSymbol);
        setAmount(amount);
    }

    public void setUserId(int userId) {
        //to validate
        this.userId = userId;
    }
    public String getCryptoSymbol() {
        return cryptoSymbol;
    }
    public void setCryptoSymbol(String cryptoSymbol) {
        //to validate
        this.cryptoSymbol = cryptoSymbol;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        //to validate
        this.amount = amount;
    }
    public int getUserId() {
        return userId;
    }

    private int userId;
    private String cryptoSymbol;
    private double amount;
}
