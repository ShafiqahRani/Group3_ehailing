/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.BookingDAO;
import dao.DriverDAO;
import model.Booking;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import model.Driver;
import model.User;

@WebServlet("/BookingController")
public class BookingController extends HttpServlet {
    private BookingDAO bookingDAO;

    public void init() {
        bookingDAO = new BookingDAO();
    }

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");

    if ("create".equals(action)) {
        createBooking(request, response);
    } else if ("cancel".equals(action)) {
    cancelBooking(request, response);
    } else if (action == null) {
    System.out.println("⚠️ Action is null");
    response.sendRedirect("jsp/user_home.jsp");
    return;
    } else if ("update_status".equals(action)) {
    int booking_id = Integer.parseInt(request.getParameter("booking_id"));
    String newStatus = request.getParameter("status");

    BookingDAO dao = new BookingDAO();
    boolean updated = dao.updateBookingStatus(booking_id, newStatus);

    if (updated) {
        request.setAttribute("successMsg", "Booking status updated successfully.");
    } else {
        request.setAttribute("errorMsg", "Failed to update booking status.");
    }

    // ✅ Reload balik view ride request
    List<Booking> bookings = dao.getPendingBookings(); // atau getAllByDriverId
    request.setAttribute("bookings", bookings);

    RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/view_ride_requests.jsp");
    dispatcher.forward(request, response);

    } else if ("book_ride".equals(action)) {
        HttpSession session = request.getSession();
        String pickup = request.getParameter("pickup_location");
        String dropoff = request.getParameter("dropoff_location");
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("user_login.jsp");
            return;
        }

        Booking booking = new Booking();
        booking.setCustomer_email(user.getEmail());
        booking.setPickup_location(pickup);
        booking.setDropoff_location(dropoff);
        booking.setBooking_time(new Timestamp(System.currentTimeMillis()));
        booking.setStatus("Pending");

        BookingDAO dao = new BookingDAO();
        int booking_id = dao.insertBookingAndReturnId(booking);

        if (booking_id > 0) {
            // ✅ Automatically assign driver
            Driver assignedDriver = dao.assignDriverToBooking(booking_id);

            if (assignedDriver != null) {
                request.setAttribute("successMsg", "Booking successful! Driver has been assigned.");
                request.setAttribute("driver", assignedDriver);
            } else {
                request.setAttribute("errorMsg", "Booking saved but no driver available.");
            }
        } else {
            request.setAttribute("errorMsg", "Failed to save booking.");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/book_ride.jsp");
        dispatcher.forward(request, response);
    }
}


    private void createBooking(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String customer_email = (String) session.getAttribute("userEmail");

        if (customer_email == null) {
            response.sendRedirect("jsp/user_login.jsp");
            return;
        }

        String pickup = request.getParameter("pickup_location");
        String dropoff = request.getParameter("dropoff_location");

        Booking booking = new Booking();
        booking.setCustomer_email(customer_email);
        booking.setPickup_location(pickup);
        booking.setDropoff_location(dropoff);
        booking.setBooking_time(new Timestamp(System.currentTimeMillis())); 
        booking.setStatus("Pending");

        boolean success = bookingDAO.insertBooking(booking);

        if (success) {
            request.setAttribute("successMsg", "Booking successfully created!");
        } else {
            request.setAttribute("errorMsg", "Booking failed.");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/user_home.jsp");
        dispatcher.forward(request, response);
    }

   private void updateBookingStatus(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    HttpSession session = request.getSession();
    Driver driver = (Driver) session.getAttribute("driver");

    if (driver == null) {
        response.sendRedirect("jsp/driver_login.jsp");
        return;
    }

    int booking_id = Integer.parseInt(request.getParameter("booking_id")); // match JSP field
    String newStatus = request.getParameter("status");

    int driver_id = driver.getId(); // from Driver object

    Booking booking = bookingDAO.getBookingById(booking_id);
    if (booking != null && booking.getDriver_id() == driver.getId()) {
        boolean updated = bookingDAO.updateBookingStatus(booking_id, newStatus);

        if (updated) {
            session.setAttribute("successMsg", "Booking status updated.");
        } else {
            session.setAttribute("errorMsg", "Failed to update status.");
        }
    } else {
        session.setAttribute("errorMsg", "You are not authorized to update this booking.");
    }

    response.sendRedirect(request.getContextPath() + "/jsp/view_ride_requests.jsp"); 
}

    
    private void cancelBooking(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    int bookingId = Integer.parseInt(request.getParameter("booking_id"));
    bookingDAO.cancelBooking(bookingId);  
    response.sendRedirect(request.getContextPath() + "/jsp/view_bookings.jsp");
}


}
