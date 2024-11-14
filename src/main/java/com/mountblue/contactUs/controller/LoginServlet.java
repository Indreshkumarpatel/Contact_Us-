package com.mountblue.contactUs.controller;



import com.mountblue.contactUs.dao.UserDao;
import com.mountblue.contactUs.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;



@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean verifyCredential;

        User userObject = new User();
        userObject.setUsername(username);
        userObject.setPassword(password);

        try {
            verifyCredential = UserDao.validate(userObject);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (verifyCredential) {
            response.sendRedirect("dashboard");
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
