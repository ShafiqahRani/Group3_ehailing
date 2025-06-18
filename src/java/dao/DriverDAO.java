/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Driver;
import java.sql.*;
import util.DBConnection;
import static util.DBConnection.getConnection;

public class DriverDAO {
    private Connection conn;

    public DriverDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ehailing_system", "student", "student_umt_2025");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // INSERT DRIVER
    public boolean insertDriver(Driver driver) {
    String sql = "INSERT INTO drivers (name, email, phone, carInfo, password) VALUES (?, ?, ?, ?, ?)";

    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, driver.getName());
        stmt.setString(2, driver.getEmail());
        stmt.setString(3, driver.getPhone());
        stmt.setString(4, driver.getCarInfo());
        stmt.setString(5, driver.getPassword());

        int rowsInserted = stmt.executeUpdate();
        return rowsInserted > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


    // CHECK IF EMAIL EXIST
    public boolean isEmailExist(String email) {
    String sql = "SELECT email FROM drivers WHERE email = ?";

    try (Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        return rs.next();

    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}


    // LOGIN VALIDATION
    public boolean validateLogin(String email, String password) {
    String sql = "SELECT * FROM drivers WHERE email = ? AND password = ?";

    try (Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, email);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        return rs.next();

    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}
    
    public Driver getDriverByEmailAndPassword(String email, String password) {
    String sql = "SELECT * FROM drivers WHERE email = ? AND password = ?";
    try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, email);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Driver driver = new Driver();
            driver.setId(rs.getInt("id"));
            driver.setName(rs.getString("name"));
            driver.setEmail(rs.getString("email"));
            driver.setPhone(rs.getString("phone"));
            driver.setCarInfo(rs.getString("carInfo"));
            driver.setPassword(rs.getString("password"));
            return driver;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

public int getDriverIdByEmail(String email) {
    String sql = "SELECT id FROM drivers WHERE email = ?";
    try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1; // Not found
}

public boolean updateDriver(Driver driver) {
    String sql = "UPDATE drivers SET name = ?, phone = ?, carInfo = ?, password = ? WHERE email = ?";
    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, driver.getName());
        stmt.setString(2, driver.getPhone());
        stmt.setString(3, driver.getCarInfo());
        stmt.setString(4, driver.getPassword());
        stmt.setString(5, driver.getEmail());
        return stmt.executeUpdate() > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


public boolean deleteDriverByEmail(String email) {
    String sql = "DELETE FROM drivers WHERE email = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, email);
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
}
