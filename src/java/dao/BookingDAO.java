/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Booking;
import model.Driver;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static util.DBConnection.getConnection;

public class BookingDAO {

    // Insert new booking
    public boolean insertBooking(Booking booking) {
        String sql = "INSERT INTO bookings (customer_email, pickup_location, dropoff_location, booking_time, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, booking.getCustomer_email());
            stmt.setString(2, booking.getPickup_location());
            stmt.setString(3, booking.getDropoff_location());
            stmt.setTimestamp(4, booking.getBooking_time());
            stmt.setString(5, booking.getStatus());

            int rowsInserted = stmt.executeUpdate();
            System.out.println("Booking insert success: " + (rowsInserted > 0));
            System.out.println("Booking time: " + booking.getBooking_time());
            return rowsInserted > 0;

        } catch (Exception e) {
            System.out.println("Error inserting booking:");
            e.printStackTrace();
            return false;
        }
    }

    // Insert booking and return ID
    public int insertBookingAndReturnId(Booking booking) {
        String sql = "INSERT INTO bookings (customer_email, pickup_location, dropoff_location, booking_time, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, booking.getCustomer_email());
            stmt.setString(2, booking.getPickup_location());
            stmt.setString(3, booking.getDropoff_location());
            stmt.setTimestamp(4, booking.getBooking_time());
            stmt.setString(5, booking.getStatus());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    // Get bookings by user email
    public List<Booking> getBookingsByEmail(String email) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE customer_email = ? ORDER BY booking_time DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking();
                booking.setId(rs.getInt("id"));
                booking.setCustomer_email(rs.getString("customer_email"));
                booking.setDriver_id(rs.getInt("driver_id"));
                booking.setPickup_location(rs.getString("pickup_location"));
                booking.setDropoff_location(rs.getString("dropoff_location"));
                booking.setBooking_time(rs.getTimestamp("booking_time"));
                booking.setStatus(rs.getString("status"));

                bookings.add(booking);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    // Get all pending bookings
    public List<Booking> getPendingBookings() {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE status = 'Pending'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setId(rs.getInt("id"));
                booking.setCustomer_email(rs.getString("customer_email"));
                booking.setPickup_location(rs.getString("pickup_location"));
                booking.setDropoff_location(rs.getString("dropoff_location"));
                booking.setBooking_time(rs.getTimestamp("booking_time"));
                booking.setStatus(rs.getString("status"));
                list.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Get accepted bookings by driver
    public List<Booking> getAcceptedBookingsByDriverId(int driver_id) {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE driver_id = ? AND status != 'Pending'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, driver_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking();
                booking.setId(rs.getInt("id"));
                booking.setCustomer_email(rs.getString("customer_email"));
                booking.setPickup_location(rs.getString("pickup_location"));
                booking.setDropoff_location(rs.getString("dropoff_location"));
                booking.setBooking_time(rs.getTimestamp("booking_time"));
                booking.setStatus(rs.getString("status"));
                list.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Get bookings assigned to a specific driver (active)
    public List<Booking> getBookingsByDriverId(int driver_id) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE driver_id = ? AND status = 'Pending'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, driver_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Booking b = new Booking();
                b.setId(rs.getInt("id"));
                b.setCustomer_email(rs.getString("customer_email"));
                b.setDriver_id(rs.getInt("driver_id"));
                b.setPickup_location(rs.getString("pickup_location"));
                b.setDropoff_location(rs.getString("dropoff_location"));
                b.setBooking_time(rs.getTimestamp("booking_time"));
                b.setStatus(rs.getString("status"));
                bookings.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }

    // Update booking status
    public boolean updateBookingStatus(int booking_id, String newStatus) {
    String sql = "UPDATE bookings SET status = ? WHERE id = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, newStatus);
        stmt.setInt(2, booking_id);

        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
}


    // Get booking by ID
    public Booking getBookingById(int booking_id) {
        String sql = "SELECT * FROM bookings WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, booking_id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Booking b = new Booking();
                b.setId(rs.getInt("id"));
                b.setCustomer_email(rs.getString("customer_email"));
                b.setDriver_id(rs.getInt("driver_id"));
                b.setPickup_location(rs.getString("pickup_location"));
                b.setDropoff_location(rs.getString("dropoff_location"));
                b.setBooking_time(rs.getTimestamp("booking_time"));
                b.setStatus(rs.getString("status"));
                return b;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Assign random driver to booking
    public Driver assignDriverToBooking(int booking_id) {
        String selectDriverSql = "SELECT * FROM drivers ORDER BY RAND() LIMIT 1";
        String updateBookingSql = "UPDATE bookings SET driver_id = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement selectStmt = conn.prepareStatement(selectDriverSql);
             ResultSet rs = selectStmt.executeQuery()) {

            if (rs.next()) {
                int driver_id = rs.getInt("id");

                try (PreparedStatement updateStmt = conn.prepareStatement(updateBookingSql)) {
                    updateStmt.setInt(1, driver_id);
                    updateStmt.setInt(2, booking_id);
                    updateStmt.executeUpdate();
                }

                Driver driver = new Driver();
                driver.setId(driver_id);
                driver.setName(rs.getString("name"));
                driver.setEmail(rs.getString("email"));
                driver.setPhone(rs.getString("phone"));
                driver.setCarInfo(rs.getString("carInfo"));
                driver.setPassword(rs.getString("password")); // Optional

                return driver;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public void cancelBooking(int booking_id) {
    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement("DELETE FROM bookings WHERE id = ?")) {
        stmt.setInt(1, booking_id);
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}
