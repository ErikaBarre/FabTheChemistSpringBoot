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

//si on a une seul query
//@NamedQuery(name="get_all_course", query="select c from Course c")
//si on a plusieurs queries
@NamedQueries(value= {
		@NamedQuery(name="get_all_course", query="select c from Course c"),
		@NamedQuery(name="get_all_course_by_name", query="select c from Course c where name like 'JPA'")
})

@Entity
@Table(name="FAB_COURSE")
public class Course {

	@Id
	@GeneratedValue
	@Column(name="CO_ID")
	private Long id;
		
	@Column(name="CO_NAME", nullable=false)
	private String name; 
	
	@UpdateTimestamp
	private LocalDateTime lastUpdateDate	;
	
	@CreationTimestamp
	private LocalDateTime createDate;
	
	protected Course() {}
	
	public Course(String name) {
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
		return "Course [id=" + id + ", name=" + name + "]";
	}

}
