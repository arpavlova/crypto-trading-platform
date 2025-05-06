package com.cryptovirtual.cryptovirtualworld.request;

public class TradeRequest {

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        // to validate
        this.userId = userId;
    }

    public String getCryptoSymbol() {
        return cryptoSymbol;
    }

    public void setCryptoSymbol(String cryptoSymbol) {
        // to validate
        this.cryptoSymbol = cryptoSymbol;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        // to validate
        this.amount = amount;
    }

    private int userId;
    private String cryptoSymbol;
    private double amount;
}
