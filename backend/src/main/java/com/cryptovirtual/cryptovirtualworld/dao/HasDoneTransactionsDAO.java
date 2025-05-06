package com.cryptovirtual.cryptovirtualworld.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HasDoneTransactionsDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertTransaction(int userId, int transactionId) {
        String sql = "INSERT INTO HasDoneTransactions (UserId, TransactionId) VALUES (?, ?)";
        jdbcTemplate.update(sql, userId, transactionId);
    }

    public void deleteTransaction(int userId, int transactionId) {
        String sql = "DELETE FROM HasDoneTransactions WHERE UserId = ? AND TransactionId = ?";
        jdbcTemplate.update(sql, userId, transactionId);
    }
}
