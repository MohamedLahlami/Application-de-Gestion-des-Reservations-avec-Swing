/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import DAO.IDAO;
import connection.Conn;
import entities.User;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author tahat
 */
public class UserService implements IDAO<User>{
    List<User> lstUsers;
    private static final int WORKLOAD = 12;

    @Override
    public boolean create(User o) {
        String req = "INSERT INTO users VALUES(null, ?, ?)";
        try {
                PreparedStatement ps = Conn.getConn().prepareStatement(req);
                ps.setString(1, o.getLogin());
                ps.setString(2, hashPassword(o.getPassword()));
                if(ps.executeUpdate() == 1) {
                        return true;
                }
        } catch (SQLException e) {
                System.out.println("Erreur SQL: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(User o) {
        String req = "DELETE FROM users WHERE id=?";
        try {
                PreparedStatement ps = Conn.getConn().prepareStatement(req);
                ps.setInt(1, o.getId());
                if (ps.executeUpdate() == 1) {
                        return true;
                }
        } catch (SQLException e) {
                System.out.println("Erreur SQL: " + e.getMessage());
        }
        return false;
    }
    
    public boolean delete(int id) {
            String req = "DELETE FROM users WHERE id=?";
            try {
                    PreparedStatement ps = Conn.getConn().prepareStatement(req);
                    ps.setInt(1, id);
                    if (ps.executeUpdate() == 1) {
                            return true;
                    }
            } catch (SQLException e) {
                    System.out.println("Erreur SQL: " + e.getMessage());
            }
            return false;
    }

    @Override
    public boolean update(User o) {
        String req = "UPDATE users SET login=?, password=? WHERE id=?";
        try {
                PreparedStatement ps = Conn.getConn().prepareStatement(req);
                ps.setString(1, o.getLogin());
                ps.setString(2, o.getPassword());
                ps.setInt(3, o.getId());
                if (ps.executeUpdate() == 1) {
                        return true;
                }
        } catch (SQLException e) {
                System.out.println("Erreur SQL: " + e.getMessage());
        }
        return false;
    }

    @Override
    public User findById(int id) {
        String req = "SELECT * FROM users WHERE id=?";
        try {
                PreparedStatement ps = Conn.getConn().prepareStatement(req);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if(rs.next()) {
                        return new User(rs.getInt(1), rs.getString(2), rs.getString(3));
                }
        } catch (SQLException e) {
                System.out.println("Erreur SQL: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        String req = "SELECT * FROM users";
        lstUsers = new ArrayList<>();
        try {
                PreparedStatement ps = Conn.getConn().prepareStatement(req);
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                        lstUsers.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3)));
                }
                return lstUsers;
        } catch (SQLException e) {
                System.out.println("Erreur SQL: " + e.getMessage());
        }
        return null;
    }
    
    public boolean authenticateUser(String login, String passwd) {
        String req = "SELECT password FROM users WHERE login = ?";

        try {
            PreparedStatement ps = Conn.getConn().prepareStatement(req);
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                String storedHash = rs.next() ? rs.getString("password") : "dummy_hash";
                return verifyPassword(passwd, storedHash);
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL: " + e.getMessage());
            return false;
        }
    }
    
    public String hashPassword(String plainTextPassword) {
        String salt = BCrypt.gensalt(WORKLOAD);
        return BCrypt.hashpw(plainTextPassword, salt);
    }
    
    public boolean verifyPassword(String plainTextPassword, String hashedPassword) {
        if (plainTextPassword == null || hashedPassword == null) {
            return false;
        }
        try {
            return BCrypt.checkpw(plainTextPassword, hashedPassword);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
