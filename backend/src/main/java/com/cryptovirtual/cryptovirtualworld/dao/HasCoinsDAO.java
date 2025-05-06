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
        String sql = "INSERT INTO HasCoins (UserId, CryptoSymbol, Amount) VALUES (?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE Amount = Amount + VALUES(Amount)";
        jdbcTemplate.update(sql, userId, symbol, amount); 
    }

    public BigDecimal getUserCoinAmount(int userId, String symbol) {
        String sql = "SELECT Amount FROM HasCoins WHERE UserId = ? AND CryptoSymbol = ?";
        return jdbcTemplate.queryForObject(sql, BigDecimal.class, userId, symbol);
    }

    public void deleteUserCoin(int userId, String symbol) {
        String sql = "DELETE FROM HasCoins WHERE UserId = ? AND CryptoSymbol = ?";
        jdbcTemplate.update(sql, userId, symbol);
    }
}
