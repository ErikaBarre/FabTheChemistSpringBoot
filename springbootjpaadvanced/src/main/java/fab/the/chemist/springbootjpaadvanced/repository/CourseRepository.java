package fab.the.chemist.springbootjpaadvanced.repository;

import java.util.List;

import javax.persistence.EntityManager;
//import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fab.the.chemist.springbootjpaadvanced.entity.Course;
import fab.the.chemist.springbootjpaadvanced.entity.Review;

@Transactional
@Repository
public class CourseRepository {

	private Logger logger = LoggerFactory.getLogger(CourseRepository.class);
	
	@Autowired
	EntityManager entityManager;
	
	public Course findById(long id){
		return entityManager.find(Course.class, id);
	}
	
	public Course save(Course course){
		if(course.getId() == null) {
			entityManager.persist(course);
		}else {
			entityManager.merge(course);  // a noter que merge fait également un insert s'il ne trouve pas l'object
		}
		/*ces ligne de code sont equivalentes à cett§e ligne
		 entityManager.merge(course);		 
		 */
		
		return course;
	}

	public void deleteById(long id){
		Course course = findById(id);
		entityManager.remove(course);
	}
	
	public void playWithEntityManager() {
		Course c = new Course("JMS");
		entityManager.persist(c);
		c.setName("ExtJs");	
		//on a un update car entitye manager continue à traqué l'object. 
		//la transaction n'est pas 
		//encore cloturée et il va faire un update sans devoir effectue un appel à la methode merge() 
	}
	
	public void playWithEntityManagerDetach() {
		Course c = new Course("Vanilla js");
		entityManager.persist(c);
		
		entityManager.flush(); // sert à effectuer une syncho avec la base de donnée
		entityManager.detach(c); // entity se détache de l'objet, l'object n'est plus lié , les mise à jour qui suive ne s'effectue plus
		c.setName("Angular js");	
				
		//si on écrit ceci sans flush alors le detach agit comme un rollback et rien n'est persisté en DB 
		Course c1 = new Course("Vanilla js ***");
		entityManager.persist(c1);
				
		entityManager.detach(c1); // entity se détache de l'objet, l'object n'est plus lié , les mise à jour qui suive ne s'effectue plus
		c1.setName("Angular js");
		
		//si on lie plusieur object à entioty manager
		Course c2 = new Course("Vanilla js");
		entityManager.persist(c2);
		
		Course c3 = new Course("Vanilla js");
		entityManager.persist(c3);
		
		entityManager.flush(); //pas oublie le flush sinon avec le clear rien n'est persisté en DB
		entityManager.clear(); //equivaut à détaché TOUS les object de l'entité manger en meme temps ( entityManager.detach(c1) entityManager.detach(c2) entityManager.detach(c3) ....)
		
		c2.setName("Angular js ++");
		c3.setName("Angular js +++");
		
		
		
		
	}
	
	public void playWithEntityManagerRefresh() {
		
		Course c2 = new Course("Vanilla js");
		entityManager.persist(c2);
		
		Course c3 = new Course("Vanilla js**");
		entityManager.persist(c3);
		
		entityManager.flush(); //ne pas oublier le flush sinon le refresh lance une exception (l'object n'est pas trouvé ) 
		
		c2.setName("Angular js ++");
		c3.setName("Angular js +++");
		entityManager.refresh(c2); //en ragrichissant les données de c2, on exclus l'update. Seul c3 sera updated et non c2
		
		
		entityManager.flush();
	}

	public void addReviewsForCourse(long courseId, List<Review> newReviews) {
		//get the course to review
		Course course = findById(courseId);
		logger.info("course info {}", course.getReviews());
		//setting the relationshipt
		for(Review newReview:newReviews) {
			course.addReview(newReview);
			newReview.setCourse(course);
			//save in DB
			entityManager.persist(newReview);
		}
		
		
	}
	
	public List<Review> retrieveReviewsForCourses(){
		//Course course = repository.
		Course course = findById(10001L);
		List<Review> reviews = course.getReviews();
		logger.info("course {}",course.getReviews());
		return reviews;
	}
	
}
