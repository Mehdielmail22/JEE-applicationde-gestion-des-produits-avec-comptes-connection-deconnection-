package tst;

public class Produit {
	private int id;
	private String nom;
	private int prix;
	public Produit(String nom) {
		super();
		this.nom = nom;
	}
	public Produit(String nom, int prix) {
		super();
		this.nom = nom;
		this.prix = prix;
	}
	public Produit() {
		super();
	}
	public Produit(int id, String nom, int prix) {
		super();
		this.id = id;
		this.nom = nom;
		this.prix = prix;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}

}
