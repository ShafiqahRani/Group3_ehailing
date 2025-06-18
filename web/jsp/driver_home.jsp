<%-- 
    Document   : driver_home
    Created on : Jun 12, 2025, 1:39:42 PM
    Author     : Acer
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="model.Driver" %>

<%
    Driver driver = (Driver) session.getAttribute("driver");
    if (driver == null) {
        response.sendRedirect("driver_login.jsp");
        return;
    }

    String driverName = driver.getName();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Driver Dashboard</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            padding: 40px 0;
            display: flex;
            justify-content: center;
        }

        .dashboard {
            width: 600px;
            background: #ffffff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 5px 20px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
        }

        .button-group form {
            margin-bottom: 15px;
        }

        button {
            width: 100%;
            padding: 12px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 8px;
            font-weight: bold;
            cursor: pointer;
            transition: background 0.3s ease;
        }

        button:hover {
            background-color: #0056b3;
        }

        .logout {
            margin-top: 30px;
            text-align: center;
        }

        .logout a {
            color: #555;
            text-decoration: none;
        }

        .logout a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="dashboard">
    <h1>Welcome, <%= driver.getName() %></h1>

    <div class="button-group">
        <form action="view_ride_requests.jsp" method="get">
            <button type="submit">📥 View Ride Requests</button>
        </form>

        <form action="driver_ride_history.jsp" method="get">
            <button type="submit">🕓 View Ride History</button>
        </form>

        <form action="driver_profile.jsp" method="get">
            <button type="submit">👤 Profile</button>
        </form>
    </div>

    <div class="logout">
        <p><a href="/ehailing/index.jsp">Logout</a></p>
    </div>
</div>

</body>
</html>
