package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.IDAO;
import connection.Conn;
import entities.Categorie;

public class CategorieService implements IDAO<Categorie>{
	List<Categorie> lstCat;
	
	@Override
	public boolean create(Categorie o) {
		String req = "INSERT INTO categorie VALUES(null, ?, ?)";
		try {
			PreparedStatement ps = Conn.getConn().prepareStatement(req);
			ps.setString(1, o.getCode());
			ps.setString(2, o.getLibelle());
			if(ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Erreur SQL: " + e.getMessage());
		}
		return false;
	}

	@Override
	public boolean delete(Categorie o) {
		String req = "DELETE FROM categorie WHERE id=?";
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
		String req = "DELETE FROM categorie WHERE id=?";
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
	public boolean update(Categorie o) {
		String req = "UPDATE categorie SET code=?, libelle=? WHERE id=?";
		try {
			PreparedStatement ps = Conn.getConn().prepareStatement(req);
			ps.setString(1, o.getCode());
			ps.setString(2, o.getLibelle());
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
	public Categorie findById(int id) {
		String req = "SELECT * FROM categorie WHERE id=?";
		try {
			PreparedStatement ps = Conn.getConn().prepareStatement(req);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return new Categorie(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
		} catch (SQLException e) {
			System.out.println("Erreur SQL: " + e.getMessage());
		}
		return null;
	}
        
       public Categorie findByLibelle(String libelle) {
		String req = "SELECT * FROM categorie WHERE libelle=?";
		try {
			PreparedStatement ps = Conn.getConn().prepareStatement(req);
			ps.setString(1, libelle);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return new Categorie(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
		} catch (SQLException e) {
			System.out.println("Erreur SQL: " + e.getMessage());
		}
		return null;
	}

	@Override
	public List<Categorie> findAll() {
		String req = "SELECT * FROM categorie";
		lstCat = new ArrayList<>();
		try {
			PreparedStatement ps = Conn.getConn().prepareStatement(req);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				lstCat.add(new Categorie(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
			return lstCat;
		} catch (SQLException e) {
			System.out.println("Erreur SQL: " + e.getMessage());
		}
		return null;
	}

}
