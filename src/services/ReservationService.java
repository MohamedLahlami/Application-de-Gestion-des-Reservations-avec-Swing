package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.IDAO;
import connection.Conn;
import entities.Client;
import entities.Reservation;
public class ReservationService implements IDAO<Reservation>{
	List<Reservation> lstRsv;
	ClientService cs = new ClientService();
	ChambreService chs = new ChambreService();

	@Override
	public boolean create(Reservation o) {
		String req = "INSERT INTO reservation VALUES(null, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = Conn.getConn().prepareStatement(req);
			ps.setDate(1, new java.sql.Date(o.getDateDebut().getTime()));
			ps.setDate(2, new java.sql.Date(o.getDateFin().getTime()));
			ps.setInt(3, o.getChambre().getId());
			ps.setInt(4, o.getClient().getId());
			if(ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Erreur SQL: " + e.getMessage());
		}
		return false;
	}

	@Override
	public boolean delete(Reservation o) {
		String req = "DELETE FROM reservation WHERE id=?";
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
		String req = "DELETE FROM reservation WHERE id=?";
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
	public boolean update(Reservation o) {
		String req = "UPDATE reservation SET dateDebut=?, dateFin=?, rsvChbr=?, rsvCl=? WHERE id=?";
		try {
			PreparedStatement ps = Conn.getConn().prepareStatement(req);
			ps.setDate(1, new java.sql.Date(o.getDateDebut().getTime()));
			ps.setDate(2, new java.sql.Date(o.getDateFin().getTime()));
			ps.setInt(3, o.getChambre().getId());
			ps.setInt(4, o.getClient().getId());
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
	public Reservation findById(int id) {
		String req = "SELECT * FROM reservation WHERE id=?";
		try {
			PreparedStatement ps = Conn.getConn().prepareStatement(req);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return new Reservation(rs.getInt(1), rs.getDate(2), rs.getDate(3), cs.findById(rs.getInt(5)), chs.findById(rs.getInt(4)));
			}
		} catch (SQLException e) {
			System.out.println("Erreur SQL: " + e.getMessage());
		}
		return null;
	}

	@Override
	public List<Reservation> findAll() {
		String req = "SELECT * FROM reservation";
		lstRsv = new ArrayList<Reservation>();
		try {
			PreparedStatement ps = Conn.getConn().prepareStatement(req);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				lstRsv.add(new Reservation(rs.getInt(1), rs.getDate(2), rs.getDate(3), cs.findById(rs.getInt(5)), chs.findById(rs.getInt(4))));
			}
			return lstRsv;
		} catch (SQLException e) {
			System.out.println("Erreur SQL: " + e.getMessage());
		}
		return null;
	}

        public List<Reservation> findByClient(Client c){
            String req = "SELECT * FROM reservation WHERE rsvCl=?";
            lstRsv = new ArrayList<Reservation>();
            try {
                    PreparedStatement ps = Conn.getConn().prepareStatement(req);
                    ps.setInt(1, c.getId());
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()) {
                            lstRsv.add(new Reservation(rs.getInt(1), rs.getDate(2), rs.getDate(3), cs.findById(rs.getInt(5)), chs.findById(rs.getInt(4))));
                    }
                    return lstRsv;
            } catch (SQLException e) {
                    System.out.println("Erreur SQL: " + e.getMessage());
            }
            return null;
        }
        
}
