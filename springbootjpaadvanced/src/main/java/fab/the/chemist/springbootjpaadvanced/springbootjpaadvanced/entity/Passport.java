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
@Table(name="FAB_PASSPORT")
public class Passport {

	@Id
	@GeneratedValue
	@Column(name="PA_ID")
	private Long id;
		
	@Column(name="PA_NUMBER", nullable=false)
	private String number; 

	@JoinColumn(name="PA_STUDENT_ID")
	@OneToOne(fetch = FetchType.LAZY, mappedBy="passport")  //en plaçant mappedby, on évite la création de la colonne student_id , jpa connait le lien
	private Student student;
	
	@UpdateTimestamp
	private LocalDateTime lastUpdateDate	;
	
	@CreationTimestamp
	private LocalDateTime createDate;
	
	protected Passport() {}
	
	public Passport(String number) {
		super();
		this.number = number;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Passport [id=" + id + ", name=" + number + "]";
	}

}
