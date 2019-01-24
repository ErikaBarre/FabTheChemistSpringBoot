package fab.the.chemist.springbootjpaadvanced.entity;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
		
	@Column(name="RE_RATING", nullable=false)
	@Enumerated(value=EnumType.STRING) //on placera en base donne l'identifiant de l'enum
	private ReviewRating rating;
	//private String rating; 
	
	@Column(name="RE_DESCRIPTION")
	private String description; 

	/**
	 * dans ce cas d'un manytoone, on constate que si on veut ramener un course sur base de la review,
	 * hibernate effectue toujours par defaut un EAGER fetching
	 * hibernate fait tout le jointure dans ce sens là 
	 */
	@JoinColumn(name = "RE_COURSE_ID")
	@ManyToOne
	private Course course;
	
	@UpdateTimestamp
	private LocalDateTime lastUpdateDate	;
	
	@CreationTimestamp
	private LocalDateTime createDate;
	
	protected Review() {}
	
	public Review(ReviewRating rating, String description) {
		super();
		this.rating = rating;
		this.description = description;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public ReviewRating getRating() {
		return rating;
	}

	public void setRating(ReviewRating rating) {
		this.rating = rating;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", rating=" + rating + ", description=" + description + ", lastUpdateDate="
				+ lastUpdateDate + ", createDate=" + createDate + "]";
	}


}
