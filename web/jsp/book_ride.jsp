<%-- 
    Document   : book_ride
    Created on : Jun 12, 2025, 12:39:43 PM
    Author     : Acer
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.User" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("user_login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Book a Ride</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 40px 0;
            background-color: #f9f9f9;
            display: flex;
            justify-content: center;
        }

        .container {
            background: #fff;
            padding: 30px 40px;
            border-radius: 12px;
            box-shadow: 0 5px 20px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 600px;
        }

        h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #333;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        label {
            font-weight: bold;
        }

        input[type="text"] {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 6px;
            width: 100%;
        }

        button {
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

        .back {
            margin-top: 20px;
            text-align: center;
        }

        .back a {
            text-decoration: none;
            color: #007bff;
            font-weight: bold;
        }

        .back a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>🚗 Book a New Ride</h2>

    <form action="${pageContext.request.contextPath}/BookingController" method="post">
    <input type="hidden" name="action" value="book_ride">

        <label for="pickup_location">Pickup Location:</label>
        <input type="text" name="pickup_location" id="pickup_location" required>

        <label for="dropoffLocation">Dropoff Location:</label>
        <input type="text" name="dropoff_location" id="dropoff_location" required>

        <button type="submit">Book Now</button>
    </form>
    <% String successMsg = (String) request.getAttribute("successMsg");
   String errorMsg = (String) request.getAttribute("errorMsg");
   model.Driver driver = (model.Driver) request.getAttribute("driver");
%>

<% if (successMsg != null) { %>
    <p style="color: green; font-weight: bold;"><%= successMsg %></p>
<% } %>

<% if (errorMsg != null) { %>
    <p style="color: red; font-weight: bold;"><%= errorMsg %></p>
<% } %>

<% if (driver != null) { %>
    <div style="margin-top: 20px; padding: 15px; border: 1px solid #ccc; border-radius: 10px; background-color: #f1f1f1;">
        <h3>🚗 Driver Assigned</h3>
        <p><strong>Name:</strong> <%= driver.getName() %></p>
        <p><strong>Email:</strong> <%= driver.getEmail() %></p>
        <p><strong>Phone:</strong> <%= driver.getPhone() %></p>
        <p><strong>Car Info:</strong> <%= driver.getCarInfo() %></p>
    </div>
<% } %>


    <div class="back">
        <a href="<%= request.getContextPath() %>/jsp/user_home.jsp">⬅ Back to Dashboard</a>
    </div>
</div>

</body>
</html>

