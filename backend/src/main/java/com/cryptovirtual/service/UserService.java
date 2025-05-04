package service;

import java.math.BigDecimal;
import java.sql.SQLException;

import model.User;

public class UserService {
    public User getUserById(int id) throws SQLException {

        return new User(); //to be replaced
    };

    public void updateBalance(int userId, BigDecimal newBalance) throws SQLException {

    } ;

}
