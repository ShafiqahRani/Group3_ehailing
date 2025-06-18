/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.UserDAO;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("register".equals(action)) {
            registerUser(request, response);
        } else if ("login".equals(action)) {
            loginUser(request, response);
        } else if ("updateProfile".equals(action)) {
            updateProfile(request, response);
        }else if ("delete_account".equals(action)) {
            deleteAccount(request, response);
}

    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            if (userDAO.isEmailExist(email)) {
                request.setAttribute("errorMsg", "Email already registered.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/user_signup.jsp");
                dispatcher.forward(request, response);
            } else {
                User user = new User();
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);

                userDAO.insertUser(user);
                request.getSession().setAttribute("successMsg", "Registration successful! Please login.");
                response.sendRedirect(request.getContextPath() + "/jsp/user_login.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMsg", "Something went wrong. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/user_signup.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userDAO.getUserByEmailAndPassword(email, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/jsp/user_home.jsp");
        } else {
            request.setAttribute("errorMsg", "Invalid email or password.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/user_login.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void updateProfile(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");

        if (currentUser == null) {
            response.sendRedirect(request.getContextPath() + "/jsp/user_login.jsp");
            return;
        }

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        currentUser.setName(name);
        currentUser.setPassword(password);

        boolean updated = userDAO.updateUser(currentUser);
        if (updated) {
            session.setAttribute("user", currentUser); // update session info
            request.setAttribute("successMsg", "Profile updated successfully.");
        } else {
            request.setAttribute("errorMsg", "Failed to update profile.");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/update_profile.jsp");
        dispatcher.forward(request, response);
    }
    
    private void deleteAccount(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
    HttpSession session = request.getSession();
    User currentUser = (User) session.getAttribute("user");

    if (currentUser == null) {
        response.sendRedirect(request.getContextPath() + "/jsp/user_login.jsp");
        return;
    }

    boolean deleted = userDAO.deleteUserByEmail(currentUser.getEmail());
    if (deleted) {
        session.invalidate(); // remove session
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    } else {
        request.setAttribute("errorMsg", "Failed to delete account.");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/update_profile.jsp");
        dispatcher.forward(request, response);
    }
}

}

