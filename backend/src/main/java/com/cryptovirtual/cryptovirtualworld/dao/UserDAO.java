package com.cryptovirtual.cryptovirtualworld.dao;
import java.math.BigDecimal;
// import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cryptovirtual.cryptovirtualworld.model.Transaction;
import com.cryptovirtual.cryptovirtualworld.model.User;

@Repository
public class UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User getUserById(int userId) {
        String sql = "SELECT * FROM Users WHERE Id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), userId);
    }

    public Double getBalanceById(int userId) {
        String sql = "SELECT Balance FROM Users WHERE Id = ?";
        return jdbcTemplate.queryForObject(sql, Double.class, userId);
    }

    public String getUsernameById(int userId) {
        String sql = "SELECT Username FROM Users WHERE Id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, userId);
    }

    public void updateBalanceById(int userId, double newBalance) {
        String sql = "UPDATE Users SET Balance = ? WHERE Id = ?";
        jdbcTemplate.update(sql, newBalance, userId);
    }

    public List<Transaction> getTransactionsByUserId(int userId) {
        String sql = "SELECT * FROM Transactions WHERE UserId = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Transaction.class), userId);
    }

    public List<Transaction> getAllTransactionsByUserId(int userId) {
        String sql = "SELECT * FROM Transactions WHERE UserId = ? ORDER BY DateOfTransaction DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Transaction.class), userId);
    }

    public BigDecimal getTotalGainsRatio(int userId, LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT GetTotalGainsRatio(?, ?, ?)";
        return jdbcTemplate.queryForObject(sql, BigDecimal.class, userId, startDate, endDate);
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM Users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public void insertUser(User user) {
        String sql = "INSERT INTO Users (Username, Balance) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getBalance());
    }

    public void updateUser(User user) {
        String sql = "UPDATE Users SET Username = ?, Balance = ? WHERE Id = ?";
        jdbcTemplate.update(sql, user.getUsername(), user.getBalance(), user.getId());
    }

    public void deleteUser(int id) {
        String sql = "DELETE FROM Users WHERE Id = ?";
        jdbcTemplate.update(sql, id);
    }
}