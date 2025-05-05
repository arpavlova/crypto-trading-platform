package com.cryptovirtual.cryptovirtualworld.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private int id;
    private String name;
    private String type;
    private LocalDateTime dateOfTransaction;
    private int userId;
    
    public Transaction(String name, String type, String cryptoSymbol, BigDecimal cryptoPrice, BigDecimal amount) {
        this.name = name;
        this.type = type;
        this.cryptoSymbol = cryptoSymbol;
        this.cryptoPrice = cryptoPrice;
        this.amount = amount;
    }
    private String cryptoSymbol;
    private BigDecimal cryptoPrice;
    private BigDecimal amount;
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public LocalDateTime getDateOfTransaction() {
        return dateOfTransaction;
    }
    public void setDateOfTransaction(LocalDateTime dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getCryptoSymbol() {
        return cryptoSymbol;
    }
    public void setCryptoSymbol(String cryptoSymbol) {
        this.cryptoSymbol = cryptoSymbol;
    }
    public BigDecimal getCryptoPrice() {
        return cryptoPrice;
    }
    public void setCryptoPrice(BigDecimal cryptoPrice) {
        this.cryptoPrice = cryptoPrice;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
