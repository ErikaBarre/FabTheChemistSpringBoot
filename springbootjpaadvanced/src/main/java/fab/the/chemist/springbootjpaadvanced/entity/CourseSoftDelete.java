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
import javax.persistence.PreRemove;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
//import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;

//SOFT DELETE
//il s'agit d'un deleted pour lequel auquel record n'est effacé en base de données
//il s'agit d'un requete qui élimine ce dont on ne veut pas voir dans le retour. 
//dans ce cas, on a ajouté un flag 
//(qui devra aussi être colonne en base de données sinon une erreur sera déclenchée) 
//qui marque ce dont on ne veut pas garder
//on utilera pour se faire les annotation @sqldelete et @where

@Entity
@Table(name="FAB_COURSE")
//on doit ajouter une hibernate annotation (concernant cette propriété isdeleted)
//pour deleted completement, on place cette propriété à true
//il faudra ajouter un champs à la table (sinon erreur au lancement de l'application)
//ensuite il faudra préciser une methode preRemove car hibernate met à jour le champ en base de données concernant la colonne isdeleted
//MAIS la valeur de la propriété isdeleted dans la classe "course" n'est pas modifiée. Hibernate n'agit pas desssus  
//preRemove est une life cycle method , il va modifié la valeur de la propriété de la classe avant que ne se déclenche la modification de la valeur dans la colonne isdeleted en DB
//il existe d'autre méthode life cycle
//postLoad postPersist postUpdate (méthode qui se déclenche après) prePersist preUpdate preRemove (methode qui se déclenche avant)
@SQLDelete(sql="update FAB_COURSE set CO_IS_DELETED=true where CO_ID=?")
@Where(clause="CO_IS_DELETED=false")
public class CourseSoftDelete {

	private static Logger logger = LoggerFactory.getLogger(CourseSoftDelete.class);
	
	//propriété pour gerer le soft delete
	//ajout d'une colonne en base de données
	@Column(name="CO_IS_DELETE")
	private boolean isDeleted ; 
	
	@PreRemove
	private void preRemove() {
		logger.info("setting isdeleted to true");
		this.isDeleted=true;
	}
	
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
	
	protected CourseSoftDelete() {}
	
	public CourseSoftDelete(String name) {
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
