<%-- 
    Document   : update_profile
    Created on : Jun 12, 2025, 12:40:43 PM
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
    <title>User Profile</title>
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

        input[type="text"],
        input[type="email"],
        input[type="password"] {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 6px;
            width: 100%;
        }

        input[readonly] {
            background-color: #f5f5f5;
        }

        button {
            padding: 12px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 8px;
            font-weight: bold;
            cursor: pointer;
            transition: background 0.3s ease;
        }

        button:hover {
            background-color: #218838;
        }

        .danger {
            background-color: #dc3545;
        }

        .danger:hover {
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
    <h2>👤 Update Profile</h2>

    <% 
    String successMsg = (String) request.getAttribute("successMsg");
    String errorMsg = (String) request.getAttribute("errorMsg");
    if (successMsg != null) { 
    %>
        <p style="color: green; text-align: center;"><%= successMsg %></p>
    <% } else if (errorMsg != null) { %>
        <p style="color: red; text-align: center;"><%= errorMsg %></p>
    <% } %>

     <form action="<%= request.getContextPath() %>/UserController" method="post">
        <input type="hidden" name="action" value="updateProfile">
        <input type="hidden" name="email" value="<%= user.getEmail() %>">

        <label for="name">Name:</label>
        <input type="text" name="name" value="<%= user.getName() %>" required>

        <label for="password">Password:</label>
        <input type="password" name="password" value="<%= user.getPassword() %>" required>

        <button type="submit">Update Profile</button>
    </form>

    <form action="<%= request.getContextPath() %>/UserController" method="post"
      onsubmit="return confirm('Are you sure you want to delete your account?');">
    <input type="hidden" name="action" value="delete_account">
    <button type="submit" style="background-color:red; color:white;">Delete Account</button>
</form>


    <div class="back">
        <a href="<%= request.getContextPath() %>/jsp/user_home.jsp">⬅ Back to Dashboard</a>
    </div>
</div>

</body>
</html>

