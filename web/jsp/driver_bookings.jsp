<%-- 
    Document   : driver_bookings
    Created on : Jun 12, 2025, 5:32:12 AM
    Author     : Acer
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, model.Booking" %>
<%
    List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Driver Bookings</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
    <div class="form-container">
        <h2>Incoming Bookings</h2>
        <table border="1" cellpadding="10">
            <tr>
                <th>ID</th>
                <th>Pickup</th>
                <th>Dropoff</th>
                <th>Status</th>
            </tr>
            <%
                if (bookings != null) {
                    for (Booking b : bookings) {
            %>
            <tr>
                <td><%= b.getId() %></td>
                <td><%= b.getPickup_location() %></td>
                <td><%= b.getDropoff_location() %></td>
                <td><%= b.getStatus() %></td>
            </tr>
            <%
                    }
                }
            %>
        </table>
    </div>
</body>
</html>
