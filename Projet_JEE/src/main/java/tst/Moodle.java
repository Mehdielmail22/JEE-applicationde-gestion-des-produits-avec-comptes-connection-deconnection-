package tst;
import java.util.ArrayList;
import java.util.List;

import tst.*;

public class Moodle {
	private List<Produit> Produits;
	public Moodle(List<Produit> produits) {
		super();
		Produits = produits;
	}
	private int n;
	public Moodle() {
		super();
	}
	public List<Produit> getProduits() {
		return Produits;
	}
	public void setProduits(List<Produit> produits) {
		Produits = produits;
	}
	public List<Produit> Search(String nom){
		List<Produit> result=new ArrayList<Produit>();
		for(int i=0;i<this.getProduits().size();i++) {
			if(this.getProduits().get(i).getNom().contains(nom)) {
				result.add(this.getProduits().get(i));
			}
			
		}
		return result;
		
	}

}
