package fab.the.chemist.springbootjpaadvanced.entity;

import javax.persistence.Embeddable;

//on ne veut pas creer de table Address, on veut juste compléter la table student
//on va utiliser l'annotation embedded
//le fait de faire un insert d'adresse en DB alors que les colonnes n'existe pas en DB ne va pas entrainer d'erreur
//pour persisté en DB il faut aussi prévoir les colonnes dans les table en DB
@Embeddable
public class Address {
	
	private String line1;
	private String line2;
	private String city;
	
	public Address() {
		super();
	}
	
	public Address(String line1, String line2, String city) {
		super();
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
	}
	
	@Override
	public String toString() {
		return "Address [line1=" + line1 + ", line2=" + line2 + ", city=" + city + "]";
	}

}
