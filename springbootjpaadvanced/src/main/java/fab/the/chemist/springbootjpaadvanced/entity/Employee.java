package fab.the.chemist.springbootjpaadvanced.entity;


import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

//annotation inheritance pour l'heritage 
//les entity employee fulltime et parttime sont en réalité une seules table en base de donnée
//les colonnes communes se trouvent dans employee
//@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
//on utilise alors une "strategie" pour signale qu'il s'agit d'une seule table 
//avantage moins de code et à l'exécution , pas de join et donc meilleures performances
//hibernate ajoute une "dtype" colonne pour signaler de quel type d'employé il s'agit 
//pour nommer cette colonne avec un nom plus parlant, utiliser l'annotation
//@DiscriminatorColumn
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS) c'est dans le cas, où dans la base de données 
//nous avons effectivement 2 table , une pour fulltime et une autre pour parttime
//un findall de tous les employee est plus gourmand en performance car hibernate crée des unions 
//mais la requete jpsql sur "Employee" est quand meme reconnue et ensuite transformée par hibernate pour effectué une union sur les 2 tables fulltime et parttime
//@Inheritance(strategy=InheritanceType.JOINED)
//dans ce cas, on a 3 table = employee , fulltime et parttime
//hibernate pour tous ramener cree des jointures (performance c'est moins bon) mais respecte 
//une bonne architecture de schéma de base de données (c'est le meilleur)
//@MappedSuperclass : si utilsé avec on n'emploit pas l'annotation @Entity
//creation d'une table fulltime, partime mais pas de table employee et les requete jpsql ne peuvent plus utiliser Employee
//les requete doivent être effectuée sur fulltime et partime (""select e from Employee e" : ne fonctionne pas car employee n'est plus une entity)
//il faut effectuer une requete différente "select e from PartTimeEmployee e"

//perso je trouve que tout ça c'est de la merde
//le mieux c'est un table employee et une autre avec une colonne montant du salaire et une autre 
//"type d'employee" (full ou part) et enfin FK vers employee

@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
//@Inheritance(strategy=InheritanceType.JOINED)
//@MappedSuperclass
@DiscriminatorColumn(name="EmployeeType")
@Entity
@Table(name="FAB_EMPLOYEE")
public abstract class Employee {

	@Id
	@GeneratedValue
	@Column(name="EM_ID")
	private Long id;
		
	@Column(name="EM_NAME", nullable=false)
	private String name;

	public Employee() {
		super();
	} 

	public Employee(String name) {
		super();
		this.name = name;
	} 
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + "]";
	}

	
	

}
