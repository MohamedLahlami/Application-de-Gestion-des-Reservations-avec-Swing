package entities;

import java.util.Date;

public class Reservation {
	private int id;
	private Client client;
	private Chambre chambre;
	private Date dateDebut;
	private Date dateFin;
	private static int count  = 0;

	public Reservation(Date dateDebut, Date dateFin, Client client, Chambre chambre) {
		super();
		this.id = ++count;
		this.client = client;
		this.chambre = chambre;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	public Reservation(int id, Date dateDebut, Date dateFin, Client client, Chambre chambre) {
		super();
		this.id = id;
		this.client = client;
		this.chambre = chambre;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Chambre getChambre() {
		return chambre;
	}

	public void setChambre(Chambre chambre) {
		this.chambre = chambre;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", client=" + client + ", chambre=" + chambre + ", dateDebut=" + dateDebut
				+ ", dateFin=" + dateFin + "]";
	}
}