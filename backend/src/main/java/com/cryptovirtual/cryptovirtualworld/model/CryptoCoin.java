package com.cryptovirtual.cryptovirtualworld.model;

import java.math.BigDecimal;

public class CryptoCoin {
    public String getSymbol() {
        return symbol;
    }
    public CryptoCoin(String symbol, BigDecimal price) {
        this.symbol = symbol;
        this.price = price;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    private String symbol;
    private BigDecimal price;
}
