package entities;

import java.util.Objects;

public class Chambre {
	private int id;
	private String tele;
	Categorie cat;
	private static int count = 0;
	
	public Chambre(String tele, Categorie cat) {
		super();
		this.id = ++count;
		this.tele = tele;
		this.cat = cat;
	}
	
	public Chambre(int id, String tele, Categorie cat) {
		super();
		this.id = id;
		this.tele = tele;
		this.cat = cat;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public Categorie getCat() {
		return cat;
	}

	public void setCat(Categorie cat) {
		this.cat = cat;
	}

	@Override
	public String toString() {
		return String.valueOf(id) + ": " + cat.getLibelle();
	}


        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Chambre other = (Chambre) obj;
            if (this.id != other.id) {
                return false;
            }
            if (!Objects.equals(this.tele, other.tele)) {
                return false;
            }
            return Objects.equals(this.cat, other.cat);
        }
        
}