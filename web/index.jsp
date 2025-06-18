<%-- 
    Document   : index
    Created on : Jun 12, 2025, 12:52:38 AM
    Author     : Acer
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Group 3</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <style>
         body {
            margin: 0;
            font-family: sans-serif;
            background-image: url('images/bg.jpg');
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
        }


        .center-wrapper {
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .hero {
            text-align: center;
            background-color: lightblue;
            padding: 50px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }

        .hero h1 {
            font-size: 36px;
            margin-bottom: 10px;
        }

        .btn-group {
            margin-top: 30px;
        }

        .btn {
            padding: 12px 30px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            margin: 10px;
            border-radius: 8px;
        }

        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="center-wrapper">
        <div class="hero">
            <h1>Welcome to SwiftRide 🚖</h1>
            <p>Please choose your role to continue:</p>

            <div class="btn-group">
                <a href="jsp/user_login.jsp" class="btn">I'm a Customer</a>
                <a href="jsp/driver_login.jsp" class="btn">I'm a Driver</a>
            </div>
        </div>
    </div>
</body>
</html>
