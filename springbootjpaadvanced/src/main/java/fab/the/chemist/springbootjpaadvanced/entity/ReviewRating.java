package fab.the.chemist.springbootjpaadvanced.entity;

public enum ReviewRating {

	//par default java place un numero derrière les constantes en suivant 
	//l'ordre de la liste définie, c'est donc un number qui sera placé en DB
	//changer dans data.sql de string en number
	//ZERO=0, ONE=1, TWO=2 etc
	ZERO, ONE, TWO, THREE, FOUR, FIVE ;
	
}
