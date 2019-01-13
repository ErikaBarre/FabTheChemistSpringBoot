package fab.the.chemist.springbootjpaadvanced.springbootjpaadvanced.entity;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
	
	
	@Override
	public String toString() {
		return "Passport [id=" + id + ", name=" + number + "]";
	}

}
