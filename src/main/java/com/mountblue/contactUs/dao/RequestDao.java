package com.mountblue.contactUs.dao;

import com.mountblue.contactUs.model.Request;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class RequestDao {

    private static final String URL = "jdbc:postgresql://localhost:5432/contactusdatabase";
    private static final String USER = "indreshpatel";
    private static final String PASSWORD = "postgres";

    // Save a new request to the database
    public static void saveRequest(Request request) {
        String sql = "INSERT INTO contact_requests (name, email, message, status) VALUES (?, ?, ?, ?)";
        try {
            Class.forName("org.postgresql.Driver");
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setString(1, request.getName());
                preparedStatement.setString(2, request.getEmail());
                preparedStatement.setString(3, request.getMessage());
                preparedStatement.setString(4, "active"); // Default status

                preparedStatement.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve all requests from the database
    public static List<Request> fetchRequests() {
        List<Request> requests = new ArrayList<>();
        String sql = "SELECT * FROM contact_requests";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Request request = new Request();
                request.setId(resultSet.getInt("id"));
                request.setName(resultSet.getString("name"));
                request.setEmail(resultSet.getString("email"));
                request.setMessage(resultSet.getString("message"));
                request.setStatus(resultSet.getString("status"));

                requests.add(request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Fetched requests: " + requests); // Debugging line
        return requests;
    }


    // Change the status of a request by ID
    public static void  changeStatus(int requestId) {
        String sql = "UPDATE contact_requests SET status = 'archive' WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, requestId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

