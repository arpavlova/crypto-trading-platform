package com.cryptovirtual.cryptovirtualworld.model;

import java.time.LocalDateTime;

public class User {
    private int id;
    private String username;
    private LocalDateTime dateOfRegistration;
    private double balance;

    public User() {}
    
    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public LocalDateTime getDateOfRegistration() {
        return dateOfRegistration;
    }
    public double getBalance() {
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
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public User(String username, double balance) {
        this.username = username;
        this.balance = balance;
    }
}
