package com.mountblue.contactUs.controller;

import com.mountblue.contactUs.dao.RequestDao;
import com.mountblue.contactUs.model.Request;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("requests", RequestDao.fetchRequests());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("dashboard.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int requestId = Integer.parseInt(request.getParameter("statusId"));
        RequestDao.changeStatus(requestId);
        response.sendRedirect("dashboard");

    }

}
