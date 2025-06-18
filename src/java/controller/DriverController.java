/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DriverDAO;
import model.Driver;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/DriverController")
public class DriverController extends HttpServlet {
    private DriverDAO driverDAO;

    @Override
    public void init() {
        driverDAO = new DriverDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("register".equals(action)) {
            registerDriver(request, response);
        } else if ("login".equals(action)) {
            loginDriver(request, response);
        } else if ("update_profile".equals(action)) {
            updateDriverProfile(request, response);
        } else if ("delete_account".equals(action)) {
            deleteAccount(request, response);
}

    }

    private void registerDriver(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String carInfo = request.getParameter("carInfo");
        String password = request.getParameter("password");

        // Check if email already exists
        if (driverDAO.isEmailExist(email)) {
            request.setAttribute("errorMsg", "Email already registered.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/driver_signup.jsp");
            dispatcher.forward(request, response);
        } else {
            Driver driver = new Driver();
            driver.setName(name);
            driver.setEmail(email);
            driver.setPhone(phone);
            driver.setCarInfo(carInfo);
            driver.setPassword(password);

           DriverDAO dao = new DriverDAO();
boolean success = dao.insertDriver(driver);

if (success) {
    HttpSession session = request.getSession();
    session.setAttribute("successMsg", "Driver registered successfully!");
    
    response.sendRedirect(request.getContextPath() + "/jsp/driver_login.jsp");
} else {
    request.setAttribute("errorMsg", "Driver registration failed. Try again.");
    request.getRequestDispatcher("jsp/driver_signup.jsp").forward(request, response);
}

        }
    }

   private void loginDriver(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    Driver driver = driverDAO.getDriverByEmailAndPassword(email, password);  // <-- kena tambah method ni

    if (driver != null) {
        HttpSession session = request.getSession();
        session.setAttribute("driver", driver);  // ✅ simpan full objek Driver
        response.sendRedirect("jsp/driver_home.jsp");
    } else {
        request.setAttribute("errorMsg", "Invalid email or password.");
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/driver_login.jsp");
        dispatcher.forward(request, response);
    }
}
   
   private void updateDriverProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String name = request.getParameter("name");
    String phone = request.getParameter("phone");
    String carInfo = request.getParameter("carInfo");
    String password = request.getParameter("password");
    String email = request.getParameter("email");

    Driver driver = new Driver();
    driver.setName(name);
    driver.setPhone(phone);
    driver.setCarInfo(carInfo);
    driver.setPassword(password);
    driver.setEmail(email);

    boolean updated = driverDAO.updateDriver(driver);
    if (updated) {
        request.setAttribute("successMsg", "Profile updated successfully.");
    } else {
        request.setAttribute("errorMsg", "Failed to update profile.");
    }

    // Update session
    request.getSession().setAttribute("driver", driver);
    request.getRequestDispatcher("jsp/driver_profile.jsp").forward(request, response);
}

private void deleteAccount(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String email = request.getParameter("email");

    DriverDAO driverDAO = new DriverDAO();
    boolean success = driverDAO.deleteDriverByEmail(email);

    if (success) {
        request.getSession().invalidate(); // clear session
        response.sendRedirect(request.getContextPath() + "/index.jsp"); // back to home
    } else {
        request.setAttribute("errorMsg", "Failed to delete account.");
        request.getRequestDispatcher("/jsp/driver_profile.jsp").forward(request, response);
    }
}



}
