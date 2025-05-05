package com.cryptovirtual.cryptovirtualworld.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class User {
    private int id;
    private String username;
    private LocalDateTime dateOfRegistration;
    private BigDecimal balance;
    
    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public LocalDateTime getDateOfRegistration() {
        return dateOfRegistration;
    }
    public BigDecimal getBalance() {
        return balance;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setDateOfRegistration(LocalDateTime dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    public User(String username, BigDecimal balance) {
        this.username = username;
        this.balance = balance;
    }
}
