<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.mountblue.contactUs.model.Request" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <style>
        /* styles.css */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
        }

        .container {
            display: flex;
            justify-content: space-around;
            padding: 20px;
        }

        .section {
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
            width: 45%;
        }

        h2 {
            margin-top: 0;
            color: #333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #f4f4f4;
        }

        button {
            background-color: #007bff;
            border: none;
            color: white;
            padding: 10px 15px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 4px;
        }

        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="section">
            <h2>Active Requests</h2>
            <table id="active-request">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Message</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                    List<Request> dataList = (List<Request>) request.getAttribute("requests");

                    // Debugging information
                    if (dataList == null) {
                        out.println("<tr><td colspan='6'>No data found</td></tr>");
                    } else {
                        out.println("<tr><td colspan='6'>Data size: " + dataList.size() + "</td></tr>");

                        for (Request requestIterator : dataList) {
                            if (requestIterator.getStatus().equals("active")) {
                    %>
                    <tr>
                        <td><%=requestIterator.getId()%></td>
                        <td><%=requestIterator.getName()%></td>
                        <td><%=requestIterator.getEmail()%></td>
                        <td><%=requestIterator.getMessage()%></td>
                        <td><%=requestIterator.getStatus()%></td>
                        <td>
                            <form action="dashboard" method="post">
                                <input type="hidden" name="statusId" value="<%=requestIterator.getId()%>">
                                <button type="submit">Archive Me</button>
                            </form>
                        </td>
                    </tr>
                    <%
                            }
                        }
                    }
                    %>
                </tbody>
            </table>
        </div>
        <div class="section">
            <h2>Archived Requests</h2>
            <table id="archive-request">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Message</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                    List<Request> dataListForArchive = (List<Request>) request.getAttribute("requests");

                    // Debugging information
                    if (dataListForArchive == null) {
                        out.println("<tr><td colspan='5'>No data found</td></tr>");
                    } else {
                        out.println("<tr><td colspan='5'>Data size: " + dataListForArchive.size() + "</td></tr>");

                        for (Request requestIterator : dataListForArchive) {
                            if (requestIterator.getStatus().equals("archive")) {
                    %>
                    <tr>
                        <td><%=requestIterator.getId()%></td>
                        <td><%=requestIterator.getName()%></td>
                        <td><%=requestIterator.getEmail()%></td>
                        <td><%=requestIterator.getMessage()%></td>
                        <td><%=requestIterator.getStatus()%></td>
                    </tr>
                    <%
                            }
                        }
                    }
                    %>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>

