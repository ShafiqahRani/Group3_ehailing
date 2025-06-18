<%-- 
    Document   : driver_profile
    Created on : Jun 12, 2025, 10:28:10 PM
    Author     : Acer
--%>

<%@ page import="model.Driver" %>
<%@ page session="true" %>
<%
    Driver driver = (Driver) session.getAttribute("driver");
    if (driver == null) {
        response.sendRedirect("driver_login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Driver Profile</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <style>
        body {
            background-color: #f4f4f4;
            font-family: sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .profile-container {
            background: white;
            padding: 40px 30px;
            border-radius: 12px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 450px;
        }

        .profile-container h2 {
            text-align: center;
            margin-bottom: 24px;
        }

        label {
            font-weight: bold;
            display: block;
            margin-bottom: 8px;
            color: #444;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 12px;
            margin-bottom: 16px;
            border: 1px solid #ccc;
            border-radius: 8px;
        }

        button {
            width: 100%;
            padding: 12px;
            font-weight: bold;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            margin-top: 10px;
        }

        .update-btn {
            background-color: #28a745;
            color: white;
        }

        .delete-btn {
            background-color: #dc3545;
            color: white;
        }

        a {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #007bff;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="profile-container">
        <h2>Driver Profile</h2>
        
        <% 
    String successMsg = (String) request.getAttribute("successMsg");
    String errorMsg = (String) request.getAttribute("errorMsg");
    if (successMsg != null) { 
    %>
        <p style="color: green; text-align: center;"><%= successMsg %></p>
    <% } else if (errorMsg != null) { %>
        <p style="color: red; text-align: center;"><%= errorMsg %></p>
    <% } %>

         <form action="<%= request.getContextPath() %>/DriverController" method="post">
            <input type="hidden" name="action" value="update_profile">
            <input type="hidden" name="email" value="<%= driver.getEmail() %>">

            <label>Name:</label>
            <input type="text" name="name" value="<%= driver.getName() %>" required>

            <label>Phone:</label>
            <input type="text" name="phone" value="<%= driver.getPhone() %>" required>

            <label>Car Info:</label>
            <input type="text" name="carInfo" value="<%= driver.getCarInfo() %>" required>

            <label>Password:</label>
            <input type="password" name="password" value="<%= driver.getPassword() %>" required>

            <button type="submit" class="update-btn">Update Profile</button>
        </form>

       <form action="<%= request.getContextPath() %>/DriverController" method="post"
             onsubmit = "return confirm('Are you sure you want to delete your account?');">
    <input type="hidden" name="action" value="delete_account">
    <input type="hidden" name="email" value="<%= driver.getEmail() %>">
    <button type="submit" style="background-color:red; color:white;">Delete Account</button>
</form>


        <a href="<%= request.getContextPath() %>/jsp/driver_home.jsp">Back to Dashboard</a>
    </div>
</body>
</html>
