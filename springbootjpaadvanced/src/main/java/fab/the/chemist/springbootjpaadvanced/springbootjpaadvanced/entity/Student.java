package fab.the.chemist.springbootjpaadvanced.springbootjpaadvanced.entity;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

	@Id
	@GeneratedValue
	@Column(name="ST_ID")
	private Long id;
		
	@Column(name="ST_NAME", nullable=false)
	private String name; 

	@JoinColumn(name="ST_PASSPORT_ID")
	@OneToOne(fetch = FetchType.LAZY)
	private Passport passport;
	
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

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}

}
