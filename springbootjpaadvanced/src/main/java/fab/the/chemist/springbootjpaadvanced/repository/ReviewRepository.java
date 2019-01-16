package fab.the.chemist.springbootjpaadvanced.repository;

import javax.persistence.EntityManager;
//import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fab.the.chemist.springbootjpaadvanced.entity.Review;

@Transactional
@Repository
public class ReviewRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager entityManager;
	
	public Review findById(long id){
		return entityManager.find(Review.class, id);
	}
	
	public Review save(Review review){
		if(review.getId() == null) {
			entityManager.persist(review);
		}else {
			entityManager.merge(review);  // a noter que merge fait Ã©galement un insert s'il ne trouve pas l'object
		}
		/*ces ligne de code sont equivalentes Ã  cettÂ§e ligne
		 entityManager.merge(Review);		 
		 */
		
		return review;
	}

	public void deleteById(long id){
		Review review = findById(id);
		entityManager.remove(review);
	}

	public void retrieveCourseForReview() {
		Review review = findById(40001L);
		logger.info("{}", review.getCourse());
		
	}
	
	
	
	
	
}
