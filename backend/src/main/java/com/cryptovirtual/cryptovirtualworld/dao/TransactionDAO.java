package com.cryptovirtual.cryptovirtualworld.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.cryptovirtual.cryptovirtualworld.model.Transaction;

@Repository
public class TransactionDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Transaction transaction) {
        String sql = "INSERT INTO Transactions (Name, Type, DateOfTransaction, UserId, CryptoSymbol, CryptoPrice, Amount) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                transaction.getName(),
                transaction.getType(),
                transaction.getDateOfTransaction(),
                transaction.getUserId(),
                transaction.getCryptoSymbol(),
                transaction.getCryptoPrice(),
                transaction.getAmount()
        );
    }

    public List<Transaction> getAllTransactions() {
        return jdbcTemplate.query("SELECT * FROM Transactions", new BeanPropertyRowMapper<>(Transaction.class));
    }

    public void insertTransaction(Transaction tx) {
        String sql = "INSERT INTO Transactions (Name, Type, UserId, CryptoSymbol, CryptoPrice, Amount) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, tx.getName(), tx.getType(), tx.getUserId(), tx.getCryptoSymbol(), tx.getCryptoPrice(), tx.getAmount());
    }

    public List<Transaction> getTransactionsByUserId(int userId) {
        String sql = "SELECT * FROM Transactions WHERE UserId = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Transaction.class), userId);
    }
}
