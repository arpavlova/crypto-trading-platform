package com.cryptovirtual.cryptovirtualworld.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.cryptovirtual.cryptovirtualworld.model.CryptoCoin;

@Repository
public class CryptoCoinDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<CryptoCoin> getAllCoins() {
        return jdbcTemplate.query("SELECT * FROM CryptoCoins", new BeanPropertyRowMapper<>(CryptoCoin.class));
    }

    public void insertCoin(CryptoCoin coin) {
        String sql = "INSERT INTO CryptoCoins (Symbol, Price) VALUES (?, ?)";
        jdbcTemplate.update(sql, coin.getSymbol(), coin.getPrice());
    }

    public void updateCoinPrice(String symbol, BigDecimal newPrice) {
        String sql = "UPDATE CryptoCoins SET Price = ? WHERE Symbol = ?";
        jdbcTemplate.update(sql, newPrice, symbol);
    }

    public void deleteCoin(String symbol) {
        String sql = "DELETE FROM CryptoCoins WHERE Symbol = ?";
        jdbcTemplate.update(sql, symbol);
    }

    public CryptoCoin getCoinBySymbol(String symbol) {
        String sql = "SELECT * FROM CryptoCoins WHERE Symbol = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(CryptoCoin.class), symbol);
    }
}
