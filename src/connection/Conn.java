package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
	private static final String url = "jdbc:mysql://localhost:3306/Gestion_des_Reservations";
	private static final String user = "root";
	private static final String password = "";
	private static Connection cnx;
	static {	
		try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				cnx = DriverManager.getConnection(url, user, password);
			} catch (ClassNotFoundException e) {
				System.out.println("Erreur Lors du chargement du pilote");
			} catch (SQLException e) {
				System.out.println("Erreur Lors de la connexion à la BD ou erreur de SQL");
			}
	}
	
	public static Connection getConn() {
		return cnx;
	}
}
