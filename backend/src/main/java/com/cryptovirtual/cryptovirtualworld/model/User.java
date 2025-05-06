package com.cryptovirtual.cryptovirtualworld.model;

import java.time.LocalDateTime;

public class User {

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
        //to validate
        this.id = id;
    }
    public void setUsername(String username) {
        //to validate
        this.username = username;
    }
    public void setDateOfRegistration(LocalDateTime dateOfRegistration) {
        //to validate
        this.dateOfRegistration = dateOfRegistration;
    }
    public void setBalance(double balance) {
        //to validate
        this.balance = balance;
    }
    public User(String username, double balance) {
        setUsername(username);
        setBalance(balance);
    }
    private int id;
    private String username;
    private LocalDateTime dateOfRegistration;
    private double balance;
}
