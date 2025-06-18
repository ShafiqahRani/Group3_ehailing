<%-- 
    Document   : user_signup
    Created on : Jun 11, 2025, 11:36:21 PM
    Author     : Acer
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up User</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <style>
        .signup-container {
            background: #fff;
            padding: 30px 40px;
            border-radius: 12px;
            box-shadow: 0 6px 20px rgba(0,0,0,0.1);
            width: 100%;
            max-width: 400px;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        label {
            font-weight: bold;
            color: #555;
        }

        input[type="text"], input[type="email"], input[type="password"] {
            width: 100%;
            padding: 10px 12px;
            margin: 8px 0 16px 0;
            border: 1px solid #ccc;
            border-radius: 8px;
            transition: 0.3s;
        }

        input:focus {
            border-color: #007bff;
            outline: none;
        }

        button {
            width: 100%;
            background: #007bff;
            color: white;
            padding: 10px 14px;
            border: none;
            border-radius: 8px;
            font-weight: bold;
            cursor: pointer;
            transition: 0.3s;
        }

        button:hover {
            background: #0056b3;
        }
    </style>
</head>
<body>
    <div class="signup-container">
        <h2>User Registration</h2>
        <p style="color:red;">${errorMsg}</p>
        <form action="<%= request.getContextPath() %>/UserController" method="post">
            <input type="hidden" name="action" value="register" />

            <label>Name:</label>
            <input type="text" name="name" required>

            <label>Email:</label>
            <input type="email" name="email" required>

            <label>Password:</label>
            <input type="password" name="password" required>

            <button type="submit">Create Account</button>
        </form>
         <div class="bottom-text">
            <p>Already have an account? <a href="user_login.jsp">Login here</a></p>
        </div>
    </div>
</body>
</html>

