package com.cryptovirtual.cryptovirtualworld.dao;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HasCoinsDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertOrUpdate(int userId, String symbol, double amount) {
        if (amount == 0) {
            deleteUserCoin(userId, symbol);
        } else {
            String sql = "INSERT INTO HasCoins (UserId, CryptoSymbol, Amount) VALUES (?, ?, ?) " +
                         "ON DUPLICATE KEY UPDATE Amount = VALUES(Amount)";
            jdbcTemplate.update(sql, userId, symbol, amount);
        }                
         
    }

    public Double getUserCoinAmount(int userId, String symbol) {
        String sql = "SELECT Amount FROM HasCoins WHERE UserId = ? AND CryptoSymbol = ?";
        return jdbcTemplate.queryForObject(sql, Double.class, userId, symbol);
    }

    public void deleteUserCoin(int userId, String symbol) {
        String sql = "DELETE FROM HasCoins WHERE UserId = ? AND CryptoSymbol = ?";
        jdbcTemplate.update(sql, userId, symbol);
    }

    public void deleteUserCoins(int userId) {
        String sql = "DELETE FROM HasCoins WHERE UserId = ?";
        jdbcTemplate.update(sql, userId);
    }
}
