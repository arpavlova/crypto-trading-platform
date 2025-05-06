package com.cryptovirtual.cryptovirtualworld.model;


public class CryptoCoin {

    public CryptoCoin() {
        // Required for Spring's BeanPropertyRowMapper
    }
    public String getSymbol() {
        return symbol;
    }
    public CryptoCoin(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    private String symbol;
    private double price;
}
