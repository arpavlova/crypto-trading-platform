package com.cryptovirtual.cryptovirtualworld.model;

public class HasCoin {
    private int userId;
    private String cryptoSymbol;
    
    public int getUserId() {
        return userId;
    }
    
    public HasCoin(int userId, String cryptoSymbol, double amount) {
        this.userId = userId;
        this.cryptoSymbol = cryptoSymbol;
        this.amount = amount;
    }

    public String getCryptoSymbol() {
        return cryptoSymbol;
    }
    public void setCryptoSymbol(String cryptoSymbol) {
        this.cryptoSymbol = cryptoSymbol;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    private double amount;
}
