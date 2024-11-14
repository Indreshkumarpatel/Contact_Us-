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

@WebServlet("/contactus")
public class ContactUsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

  /*  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("contactUs.jsp");
        requestDispatcher.forward(request, response);

    }
*/

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("message");

        Request requestObject=new Request();
        requestObject.setName(name);
        requestObject.setEmail(email);
        requestObject.setMessage(message);

        RequestDao.saveRequest(requestObject);
        response.sendRedirect("Welcome.jsp");


    }

}


