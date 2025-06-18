<%-- 
    Document   : user_home
    Created on : Jun 12, 2025, 12:18:40 AM
    Author     : Acer
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="model.User" %>

<%
    User user = (User) session.getAttribute("user");

    if (user == null) {
        response.sendRedirect("user_login.jsp");
        return;
    }

    String userEmail = user.getEmail();
%>

<!DOCTYPE html>
<html>
<head>
    <title>User Dashboard</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .spacer {
            height: 60px;
        }

        .dashboard-container {
            width: 100%;
            max-width: 600px;
            background: #ffffff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 5px 20px rgba(0, 0, 0, 0.05);
        }

        h1 {
            text-align: center;
            margin-bottom: 30px;
            color: #333;
        }

        .button-group {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        .button-group form {
            display: flex;
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
            font-weight: bold;
        }

        .logout a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="spacer"></div>

<div class="dashboard-container">
    <h1>Welcome, <%= user.getName() %></h1>

    <div class="button-group">
        <form action="book_ride.jsp" method="get">
            <button type="submit">🚗 Book a Ride</button>
        </form>
        <form action="update_profile.jsp" method="get">
            <button type="submit">📝 Profile</button>
        </form>
        <form action="view_bookings.jsp" method="get">
            <button type="submit">📜 View Booking History</button>
        </form>
        <form action="cancel_booking.jsp" method="get">
            <button type="submit">❌ Cancel Booking</button>
        </form>
    </div>
</div>

<div class="logout">
    <p><a href="/ehailing/index.jsp">Logout</a></p>
</div>

</body>
</html>

