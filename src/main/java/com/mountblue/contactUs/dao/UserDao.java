package com.mountblue.contactUs.dao;

import com.mountblue.contactUs.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private static final String URL = "jdbc:postgresql://localhost:5432/contactusdatabase";
    private static final String USER = "indreshpatel";
    private static final String PASSWORD = "postgres";

    // Method to validate user credentials
    public static boolean validate(User login) throws ClassNotFoundException {
        boolean status = false;

        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        Class.forName("org.postgresql.Driver");

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, login.getUsername());
            preparedStatement.setString(2, login.getPassword());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                status = resultSet.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    // (Optional) Method to register a new user (you can modify it based on your requirement)
    public void register(User login) throws ClassNotFoundException {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";

        Class.forName("org.postgresql.Driver");

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, login.getUsername());
            preparedStatement.setString(2, login.getPassword());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
