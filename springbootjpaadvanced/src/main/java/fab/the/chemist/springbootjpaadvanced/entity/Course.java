package fab.the.chemist.springbootjpaadvanced.entity;


import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

//si on a une seul query
//@NamedQuery(name="get_all_course", query="select c from Course c")
//si on a plusieurs queries

//CACHE
//#le deuxième niveau  de cache demande de la configuration
//#il faut signaler au framework quel données iront dans le 2eme niveau de cache
//#modification du pom.xml
//		org.hibernate
//		hibernate-ehcache

//second level cache dans application.properties
//spring.jpa.properties.hibernate.cache.use_second_level_cache=true
//specifier le type de cache , dans notre cas c'est EhCache
//spring.jpa.properties.hibernate.cache.region.factory_class=org.hibernate.cache.ehcache.EhCacheRegionFactory
//seulement placer en cache les données dont on veut qu'elle soit en cache
//#voirs les options possible dans la classe, ici on utlise ENABLE_SELECTIVE 
//spring.jpa.properties.javax.persistent.sharedCache.mode=ENABLE_SELECTIVE  
//logging.level.net.sf.ehcache=debug
//what data to cache
//placer l'annotation @Cacheable dans l'entity comme "Course" par exemple
//dans les logs on voit apparaitre "L2C"
//"hits" obtenir les data du 2eme niveau de cache 
//"misses" quand les données ne sont pas dnas le 2eme niveau de cache
//"puts" quand les données vont aller dans le 2eme niveau de cache poour la 1er fois car "misses"

@NamedQueries(value= {
		@NamedQuery(name="get_all_course", query="select c from Course c"),
		@NamedQuery(name="get_all_course_by_name", query="select c from Course c where name like 'JPA'")
})
@NamedNativeQueries(value= {
		@NamedNativeQuery(name = "selectAuthorNames", query = "SELECT * FROM FAB_COURSE", resultClass = Course.class),
		@NamedNativeQuery (name="get_all_course_n", query="select * from FAB_COURSE"),
		@NamedNativeQuery(name="get_all_course_by_name_n", query="select * from FAB_COURSE where CO_NAME like 'JPA'")
})
@Entity
@Table(name="FAB_COURSE")
@Cacheable
public class Course {

	@Id
	@GeneratedValue
	@Column(name="CO_ID")
	private Long id;
		
	@Column(name="CO_NAME", nullable=false)
	private String name; 
	
	/**
	 * il peut y avoir plueisuer review par cours
	 * donc on va placer la FK dans review
	 * donc on va mapper (mappedBy) onetomany sur l'identifaint du champs (pas l'identifiant de classe) dans review , ici "course"
	 * le but est de pouvoir placer des reveiw aussi bien en partant d'un object course (setReview) qu'en partant d'un object review (addReview) 
	 * au final c'est la colonne FK de review qui sera remplie
	 * 
	 * fetch type : si eager alors hibernate effectue des requetes à jointure
	 * si lazy, hibernate n'effectue pas de requete à jointure, il effectue une 2eme requete sur review si les reveiws sont demande
	 * lazy : ramené les données à la demande (et pas automatiquement meme si on n'a pas besoin comme pour eager 
	 * 
	 * LAZY est le mode par defaut pour onetomany
	 * 
	 * un manytomany est aussi toujours lazy fetching
	 * 
	 */ 
	@Column(name="CO_REVIEW_ID")
	@OneToMany(mappedBy="course",fetch=FetchType.LAZY)
	private List<Review> reviews;
	
	/**
	 * c'est l'orm qui est mappedby, on ne va donc pas definir la table intermédiare dans cette classe 
	 * mais dans l'autres avec les colonnes FK 
	 */
	@ManyToMany(mappedBy="courses")
	@JsonIgnore
	private List<Student> students;	
	
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
	
	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	/**
	 * ajout d'un méthode pour ajouter review par reveiw dans la pratique on ajoute pas un batch de review
	 */
	public void addReview(Review review) {
		this.reviews.add(review);
	}

	public void removeReview(Review review) {
		this.reviews.remove(review);
	}
		
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	public void addStudent(Student student) {
		this.students.add(student);
	}

	public void removeStudent(Student student) {
		this.students.remove(student);
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + "]";
	}

}
