<%-- 
    Document   : driver_login
    Created on : Jun 12, 2025, 3:05:16 AM
    Author     : Acer
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Driver Login</title>
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
            max-width: 400px;
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
        <h2>Driver Login</h2>
                <%
    String successMsg = (String) session.getAttribute("successMsg");
    if (successMsg != null && !successMsg.isEmpty()) {
%>
    <p style="color: green; font-weight: bold;"><%= successMsg %></p>
<%
        session.removeAttribute("successMsg");
    }

    String errorMsg = (String) request.getAttribute("errorMsg");
    if (errorMsg != null && !errorMsg.isEmpty()) {
%>
    <p style="color: red;"><%= errorMsg %></p>
<%
    }
%>

        <form action="<%= request.getContextPath() %>/DriverController" method="post">
            <input type="hidden" name="action" value="login">
            <label>Email:</label>
            <input type="email" name="email" required>
            <label>Password:</label>
            <input type="password" name="password" required>
            <button type="submit">Login</button>
        </form>
        <div class="bottom-text">
            <p>Don't have a SwiftRide account? <a href="/ehailing/jsp/driver_signup.jsp">Create an account</a></p>
        </div>
    </div>
</body>
</html>
