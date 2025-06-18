<%-- 
    Document   : cancel_booking
    Created on : Jun 12, 2025, 12:43:51 PM
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
    <title>Cancel Booking</title>
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

        input[type="number"] {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 6px;
            width: 100%;
        }

        button {
            padding: 12px;
            background-color: #dc3545;
            color: white;
            border: none;
            border-radius: 8px;
            font-weight: bold;
            cursor: pointer;
            transition: background 0.3s ease;
        }

        button:hover {
            background-color: #c82333;
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
    <h2>❌ Cancel a Booking</h2>

    <form action="<%= request.getContextPath() %>/BookingController" method="post">
        <input type="hidden" name="action" value="cancel">

        <label for="booking_id">Booking ID:</label>
        <input type="number" name="booking_id" id="booking_id" required>

        <button type="submit">Cancel Booking</button>
    </form>

    <div class="back">
        <a href="<%= request.getContextPath() %>/jsp/user_home.jsp">⬅ Back to Dashboard</a>
    </div>
</div>

</body>
</html>

