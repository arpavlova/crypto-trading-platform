package com.cryptovirtual.cryptovirtualworld.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HasDoneTransactionsDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(int userId, int transactionId) {
        String sql = "INSERT INTO HasDoneTransactions (UserId, TransactionId) VALUES (?, ?)";
        jdbcTemplate.update(sql, userId, transactionId);
    }

    public List<Integer> getTransactionIdsByUser(int userId) {
        String sql = "SELECT TransactionId FROM HasDoneTransactions WHERE UserId = ?";
        return jdbcTemplate.queryForList(sql, Integer.class, userId);
    }

    public void delete(int userId, int transactionId) {
        String sql = "DELETE FROM HasDoneTransactions WHERE UserId = ? AND TransactionId = ?";
        jdbcTemplate.update(sql, userId, transactionId);
    }
}
