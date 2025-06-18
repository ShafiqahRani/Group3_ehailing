<%-- 
    Document   : view_bookings
    Created on : Jun 12, 2025, 12:42:27 PM
    Author     : Acer
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Booking" %>
<%@ page import="model.User" %>
<%@ page import="dao.BookingDAO" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("user_login.jsp");
        return;
    }

    String userEmail = user.getEmail();
    BookingDAO bookingDAO = new BookingDAO();
    List<Booking> bookings = bookingDAO.getBookingsByEmail(userEmail);
%>

<!DOCTYPE html>
<html>
<head>
    <title>Booking History</title>
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
            box-shadow: 0 5px 20px rgba(0,0,0,0.1);
            width: 90%;
            max-width: 900px;
        }

        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }

        th, td {
            padding: 12px;
            border-bottom: 1px solid #ddd;
            text-align: center;
        }

        th {
            background-color: #007bff;
            color: white;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        .no-booking {
            text-align: center;
            font-size: 16px;
            color: #666;
            margin-top: 20px;
        }

        .back {
            margin-top: 30px;
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
    <h1>Your Booking History</h1>

    <% if (bookings != null && !bookings.isEmpty()) { %>
        <table>
            <tr>
                <th>Booking ID</th>
                <th>Pickup</th>
                <th>Dropoff</th>
                <th>Status</th>
                <th>Time</th>
            </tr>
            <% for (Booking b : bookings) { %>
                <tr>
                    <td><%= b.getId() %></td>
                    <td><%= b.getPickup_location() %></td>
                    <td><%= b.getDropoff_location() %></td>
                    <td><%= b.getStatus() %></td>
                    <td><%= b.getBooking_time() %></td>
                </tr>
            <% } %>
        </table>
    <% } else { %>
        <p class="no-booking">You have no bookings yet.</p>
    <% } %>

    <div class="back">
        <a href="<%= request.getContextPath() %>/jsp/user_home.jsp">⬅ Back to Dashboard</a>
    </div>
</div>

</body>
</html>
