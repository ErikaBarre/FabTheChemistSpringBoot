package fab.the.chemist.springbootjpaadvanced.entity;


import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="FAB_STUDENT")
public class Student {

	//on ne veut pas creer de table Address, on veut juste compléter la table student
	@Embedded
	private Address address;
	
	@Id
	@GeneratedValue
	@Column(name="ST_ID")
	private Long id;
		
	@Column(name="ST_NAME", nullable=false)
	private String name; 

	@JoinColumn(name="ST_PASSPORT_ID")
	@OneToOne(fetch = FetchType.LAZY)
	private Passport passport;
	
	/**
	 * on definit la table intermédiaire du coté qui n'est pas mappedby
	 * c'est l'autre orm qui sera mappedby (ici "course")
	 * on definit la colonne FK correspondant à l'ID de cette classe
	 * et la colonne (inverse) correspondant à l'ID de la classe dont la colonne est mappedby
	 * 
	 * super avantage : pas besoin de créer une table intermédiaire dans les ORM, jpa pourra effectuer des modifications dessus 
	 */
	@JoinTable(name="FAB_COURSE_STUDENT",
			joinColumns=@JoinColumn(name="CS_STUDENT_ID"),
			inverseJoinColumns=@JoinColumn(name="CS_COURSE_ID")
			)
	@ManyToMany
	private List<Course> courses;
	
	@UpdateTimestamp
	private LocalDateTime lastUpdateDate	;
	
	@CreationTimestamp
	private LocalDateTime createDate;
	
	protected Student() {}
	
	public Student(String name) {
		super();
		this.name = name;
	}
	
	public Student(String name, Passport passport) {
		super();
		this.name = name;
		this.passport = passport;
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
	
	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public void addCourse(Course course) {
		this.courses.add(course);
	}

	public void removeCourse(Course course) {
		this.courses.remove(course);
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}

}
