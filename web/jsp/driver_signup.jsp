<%-- 
    Document   : driver_signup
    Created on : Jun 12, 2025, 3:37:37 AM
    Author     : Acer
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Driver Sign Up</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <style>
        body {
            background-color: #f1f1f1;
            font-family: sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .form-container {
            background: #ffffff;
            padding: 40px 30px;
            border-radius: 12px;
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 450px;
        }

        .form-container h2 {
            text-align: center;
            margin-bottom: 24px;
            color: #333;
        }

        label {
            font-weight: bold;
            display: block;
            margin-bottom: 8px;
            color: #555;
        }

        input[type="text"],
        input[type="email"],
        input[type="password"] {
            width: 100%;
            padding: 12px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 8px;
            box-sizing: border-box;
            transition: border-color 0.3s;
        }

        input:focus {
            border-color: #007bff;
            outline: none;
        }

        button {
            width: 100%;
            background: #007bff;
            color: white;
            padding: 12px;
            border: none;
            border-radius: 8px;
            font-weight: bold;
            cursor: pointer;
            transition: 0.3s;
        }

        button:hover {
            background: #0056b3;
        }

        .error-message {
            color: red;
            font-weight: bold;
            text-align: center;
            margin-top: 10px;
        }

        .bottom-text {
            text-align: center;
            margin-top: 20px;
        }

        .bottom-text a {
            color: #007bff;
            text-decoration: none;
        }

        .bottom-text a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Driver Sign Up</h2>
        <%
    String errorMsg = (String) request.getAttribute("errorMsg");
    if (errorMsg != null && !errorMsg.isEmpty()) {
%>
    <p class="error-message"><%= errorMsg %></p>
<%
    }
%>

        <form action="../DriverController" method="post">
            <input type="hidden" name="action" value="register">
            
            <label>Full Name:</label>
            <input type="text" name="name" required>
            
            <label>Email:</label>
            <input type="email" name="email" required>
            
            <label>Phone Number:</label>
            <input type="text" name="phone" required>
            
            <label>Car Information:</label>
            <input type="text" name="carInfo" placeholder="e.g., Proton Saga 2021 - ABC1234" required>
            
            <label>Password:</label>
            <input type="password" name="password" required>
            
            <button type="submit">Create Account</button>
        </form>
        <div class="bottom-text">
            <p>Already have an account? <a href="driver_login.jsp">Login here</a></p>
        </div>
    </div>
</body>
</html>
