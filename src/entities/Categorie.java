package entities;

import java.util.Objects;

public class Categorie {
	private int id;
	private String code;
	private String libelle;
	private static int count = 0;
	
	public Categorie(String code, String libelle) {
		super();
		this.id = ++count;
		this.code = code;
		this.libelle = libelle;
	}

	public Categorie(int id, String code, String libelle) {
		super();
		this.id = id;
		this.code = code;
		this.libelle = libelle;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return libelle;
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
            final Categorie other = (Categorie) obj;
            if (this.id != other.id) {
                return false;
            }
            if (!Objects.equals(this.code, other.code)) {
                return false;
            }
            return Objects.equals(this.libelle, other.libelle);
        }
}
