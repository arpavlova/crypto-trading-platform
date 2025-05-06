package com.cryptovirtual.cryptovirtualworld.model;

public class CryptoCoin {

    public CryptoCoin() {
    }
    public String getSymbol() {
        return symbol;
    }
    public CryptoCoin(String symbol, double price) {
        setSymbol(symbol);
        setPrice(price);
    }
    public void setSymbol(String symbol) {
        //to validate
        this.symbol = symbol;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        //to validate
        this.price = price;
    }
    private String symbol;
    private double price;
}
