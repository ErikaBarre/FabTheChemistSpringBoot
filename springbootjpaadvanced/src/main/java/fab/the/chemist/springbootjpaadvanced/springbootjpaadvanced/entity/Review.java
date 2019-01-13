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
@Table(name="FAB_REVIEW")
public class Review {

	@Id
	@GeneratedValue
	@Column(name="RE_ID")
	private Long id;
		
	@Column(name="RE_DESCRIPTION", nullable=false)
	private String description; 

	@UpdateTimestamp
	private LocalDateTime lastUpdateDate	;
	
	@CreationTimestamp
	private LocalDateTime createDate;
	
	protected Review() {}
	
	public Review(String description) {
		super();
		this.description = description;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Review [id=" + id + ", name=" + description + "]";
	}

}
