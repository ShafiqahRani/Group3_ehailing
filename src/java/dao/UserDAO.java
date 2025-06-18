/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.User;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static util.DBConnection.getConnection;

public class UserDAO {
    public void insertUser(User user) {
        String sql = "INSERT INTO users(name, email, password) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean validateLogin(String email, String password) {
    boolean isValid = false;
    String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
    
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
         
        stmt.setString(1, email);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            isValid = true;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return isValid;
}
public boolean isEmailExist(String email) {
    boolean exists = false;
    try {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM users WHERE email = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        exists = rs.next(); // true kalau ada result
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return exists;
}

public User getUserByEmailAndPassword(String email, String password) {
    User user = null;
    String sql = "SELECT * FROM users WHERE email = ? AND password = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, email);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return user;
}

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                list.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public User getUserById(int id) {
        User u = null;
        String sql = "SELECT * FROM users WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = new User();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    public boolean updateUser(User user) {
    String sql = "UPDATE users SET name = ?, password = ? WHERE email = ?";
    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, user.getName());
        stmt.setString(2, user.getPassword());
        stmt.setString(3, user.getEmail());

        int rowsUpdated = stmt.executeUpdate();
        return rowsUpdated > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

    public void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean deleteUserByEmail(String email) {
    String sql = "DELETE FROM users WHERE email = ?";
    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, email);
        return stmt.executeUpdate() > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

}
