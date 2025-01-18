package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.IDAO;
import connection.Conn;
import entities.Chambre;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChambreService implements IDAO<Chambre>{
	List<Chambre> lstChbr;
	CategorieService cs = new CategorieService();
	
	@Override
	public boolean create(Chambre o) {
		String req = "INSERT INTO chambre VALUES(null, ?, ?)";
		try {
			PreparedStatement ps = Conn.getConn().prepareStatement(req);
			ps.setString(1, o.getTele());
			ps.setInt(2, o.getCat().getId());
			if(ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Erreur SQL: " + e.getMessage());
		}
		return false;
	}

	@Override
	public boolean delete(Chambre o) {
		String req = "DELETE FROM chambre WHERE id=?";
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
		String req = "DELETE FROM chambre WHERE id=?";
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
	public boolean update(Chambre o) {
		String req = "UPDATE chambre SET tele=?, catId=? WHERE id=?";
		try {
			PreparedStatement ps = Conn.getConn().prepareStatement(req);
			ps.setString(1, o.getTele());
			ps.setInt(2, o.getCat().getId());
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
	public Chambre findById(int id) {
		String req = "SELECT * FROM chambre WHERE id=?";
		CategorieService cs = new CategorieService();
		try {
			PreparedStatement ps = Conn.getConn().prepareStatement(req);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return new Chambre(rs.getInt(1), rs.getString(2), cs.findById(rs.getInt(3)));
			}
		} catch (SQLException e) {
			System.out.println("Erreur SQL: " + e.getMessage());
		}
		return null;
	}
        
        public Chambre findByLibelle(String libelle){
                String req = "SELECT * FROM chambre WHERE libelle=?";
		CategorieService cs = new CategorieService();
		try {
			PreparedStatement ps = Conn.getConn().prepareStatement(req);
			ps.setString(1, libelle);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return new Chambre(rs.getInt(1), rs.getString(2), cs.findById(rs.getInt(3)));
			}
		} catch (SQLException e) {
			System.out.println("Erreur SQL: " + e.getMessage());
		}
		return null;
        }

	@Override
	public List<Chambre> findAll() {
		String req = "SELECT * FROM chambre";
		lstChbr = new ArrayList<>();
		try {
			PreparedStatement ps = Conn.getConn().prepareStatement(req);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				lstChbr.add(new Chambre(rs.getInt(1), rs.getString(2), cs.findById(rs.getInt(3))));
			}
                        return lstChbr;
		} catch (SQLException e) {
			System.out.println("Erreur SQL: " + e.getMessage());
		}
		return null;
	}

        public Map<String, Integer> getChambersCountByCategory() {
            Map<String, Integer> categorieData = new HashMap<>();
            String req = "SELECT cat.libelle, COUNT(*) AS count FROM chambre c JOIN categorie cat ON c.catId = cat.id GROUP BY cat.libelle;";
            PreparedStatement ps;
            try {
                ps = Conn.getConn().prepareStatement(req);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    categorieData.put(rs.getString("libelle"), rs.getInt("count"));
                }
            } catch (SQLException e) {
                    System.out.println("Erreur SQL: " + e.getMessage());
            }
            return categorieData;
        }

}
