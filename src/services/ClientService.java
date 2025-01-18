package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DAO.IDAO;
import connection.Conn;
import entities.Client;

public class ClientService implements IDAO<Client>{
	List<Client> lstCl;
	
	@Override
	public boolean create(Client o) {
		String req = "INSERT INTO client VALUES(null, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = Conn.getConn().prepareStatement(req);
			ps.setString(1, o.getNom());
			ps.setString(2, o.getPrenom());
			ps.setString(3, o.getTele());
			ps.setString(4, o.getEmail());
			if(ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Erreur SQL: " + e.getMessage());
		}
		return false;
	}

	@Override
	public boolean delete(Client o) {
		String req = "DELETE FROM client WHERE id=?";
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
		String req = "DELETE FROM client WHERE id=?";
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
	public boolean update(Client o) {
		String req = "UPDATE client SET nom=?, prenom=?, telephone=?, email=? WHERE id=?";
		try {
			PreparedStatement ps = Conn.getConn().prepareStatement(req);
			ps.setString(1, o.getNom());
			ps.setString(2, o.getPrenom());
			ps.setString(3, o.getTele());
			ps.setString(4, o.getEmail());
			ps.setInt(5, o.getId());
			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Erreur SQL: " + e.getMessage());
		}
		return false;
	}

	@Override
	public Client findById(int id) {
		String req = "SELECT * FROM client WHERE id=?";
		try {
			PreparedStatement ps = Conn.getConn().prepareStatement(req);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
		} catch (SQLException e) {
			System.out.println("Erreur SQL: " + e.getMessage());
		}
		return null;
	}

	@Override
	public List<Client> findAll() {
		String req = "SELECT * FROM client";
		lstCl = new ArrayList<>();
		try {
			PreparedStatement ps = Conn.getConn().prepareStatement(req);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				lstCl.add(new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
			}
			return lstCl;
		} catch (SQLException e) {
			System.out.println("Erreur SQL: " + e.getMessage());
		}
		return null;
	}
        
}
