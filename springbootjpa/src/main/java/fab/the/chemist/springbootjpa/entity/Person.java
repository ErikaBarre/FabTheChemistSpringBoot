package fab.the.chemist.springbootjpa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



@Entity
@NamedQuery(name="find_all_persons", query="select p from Person p")
@Table(name="FAB_PERSON")
public class Person {
	
	@Id
	@Column(name="PE_ID", nullable = false)
	@GeneratedValue
	private int id;
	
	@Column(name="PE_NAME")
	private String name;
	
	@Column(name="PE_LOCATION")
	private String location;
	
	@Column(name="PE_BIRTH_DATE")
	private Date birthDate;

	public Person() {
		
	}

	/**
	 * constructeur sans id car il est auto generé (annotation @GeneratedValue) 
	 * @param name
	 * @param location
	 * @param birthDate
	 */
	public Person(String name, String location, Date birthDate) {
		super();
		this.name = name;
		this.location = location;
		this.birthDate = birthDate;
	}
	
	public Person(int id, String name, String location, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.birthDate = birthDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return String.format("\nPerson [id=%s, name=%s, location=%s, birthDate=%s]", id, name, location, birthDate);
	}

	
	
}
