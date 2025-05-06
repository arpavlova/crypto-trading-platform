package com.cryptovirtual.cryptovirtualworld.model;

public class Holding {
    
    public Holding() {}
    
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
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    private String cryptoSymbol;
    private double amount;
    private double price;
}
