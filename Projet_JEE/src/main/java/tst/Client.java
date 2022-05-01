package tst;

public class Client {
   private String nom;
   private String email;
   private String password;
public Client(String nom) {
	super();
	this.nom = nom;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public Client(String email, String password) {
	super();
	this.email = email;
	this.password = password;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
}
