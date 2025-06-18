<%-- 
    Document   : driver_ride_history
    Created on : Jun 12, 2025, 2:02:10 PM
    Author     : Acer
--%>

<%@ page import="java.util.List" %>
<%@ page import="model.Driver" %>
<%@ page import="model.Booking" %>
<%@ page import="dao.BookingDAO" %>

<%
    Driver driver = (Driver) session.getAttribute("driver");
    if (driver == null) {
        response.sendRedirect("driver_login.jsp");
        return;
    }

    BookingDAO bookingDAO = new BookingDAO();
    List<Booking> bookings = bookingDAO.getAcceptedBookingsByDriverId(driver.getId());
%>

<!DOCTYPE html>
<html>
<head>
    <title>Your Ride History</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #eef2f7;
            display: flex;
            justify-content: center;
            align-items: flex-start;
            min-height: 100vh;
            padding: 40px;
        }

        .card {
            background-color: white;
            padding: 30px 40px;
            border-radius: 12px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.08);
            width: 100%;
            max-width: 1000px;
        }

        h1 {
            text-align: center;
            color: #2c3e50;
            margin-bottom: 30px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }

        th, td {
            padding: 14px 10px;
            text-align: center;
            border-bottom: 1px solid #e0e0e0;
        }

        th {
            background-color: #007bff;
            color: white;
            text-transform: uppercase;
            font-size: 14px;
        }

        tr:hover {
            background-color: #f8f9fa;
        }

        .no-data {
            text-align: center;
            color: #777;
            font-size: 16px;
            margin-top: 20px;
        }

        .back {
            margin-top: 30px;
            text-align: center;
        }

        .back a {
            text-decoration: none;
            color: #007bff;
            font-weight: 500;
            font-size: 15px;
        }

        .back a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="card">
    <h1>Your Ride History</h1>

    <a href="<%= request.getContextPath() %>/BookingController"></a> 
    <% if (bookings != null && !bookings.isEmpty()) { %>
        <table>
            <tr>
                <th>Booking ID</th>
                <th>Customer</th>
                <th>Pickup</th>
                <th>Dropoff</th>
                <th>Time</th>
                <th>Status</th>
            </tr>
            <% for (Booking b : bookings) { %>
                <tr>
                    <td><%= b.getId() %></td>
                    <td><%= b.getCustomer_email() %></td>
                    <td><%= b.getPickup_location() %></td>
                    <td><%= b.getDropoff_location() %></td>
                    <td><%= b.getBooking_time() %></td>
                    <td><%= b.getStatus() %></td>
                </tr>
            <% } %>
        </table>
    <% } else { %>
        <p class="no-data">No ride history yet.</p>
    <% } %>

    <div class="back">
        <p><a href="driver_home.jsp"> Back to Dashboard</a></p>
    </div>
</div>

</body>
</html>
