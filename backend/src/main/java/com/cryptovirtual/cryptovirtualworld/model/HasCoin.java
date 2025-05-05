package com.cryptovirtual.cryptovirtualworld.model;

import java.math.BigDecimal;

public class HasCoin {
    private int userId;
    private String cryptoSymbol;
    
    public int getUserId() {
        return userId;
    }
    
    public HasCoin(int userId, String cryptoSymbol, BigDecimal amount) {
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
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    private BigDecimal amount;
}
