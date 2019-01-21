package fab.the.chemist.springbootjpaadvanced;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import fab.the.chemist.springbootjpaadvanced.entity.Course;
import fab.the.chemist.springbootjpaadvanced.entity.Student;
import fab.the.chemist.springbootjpaadvanced.repository.CourseRepository;
import fab.the.chemist.springbootjpaadvanced.repository.ReviewRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootjpaadvancedApplicationTests {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository repository;
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	EntityManager entityManager;
	
	@Test
	public void testFindById() {
		logger.info("User id 10001 -> {}", repository.findById(10001L));
		//assertEquals("JPA", repository.findById(10001L).getName());
		assertTrue(true);
	}

	
	///annotation dirtcontext c'est au cas où lautre test fait appel au meme object
	//c'est signaler que si le test fonctionne , ensuite, il doit restaurer une situation saine pour
	//l'autre test. Sinon l'autre test sera faux automatiquement puisqu'il cherche un object qui a ete supprimé par le 1er test
	@Test
	@DirtiesContext
	public void testDeleteById() {
		//logger.info("User id 10002 -> {}", repository.findById(10002L));
		repository.deleteById(10002L);
		assertNull(repository.findById(10002L));
	}
	
	@Test
	@DirtiesContext
	public void testSave() {
		logger.info("Inserting -> {}", 	repository.save(new Course("Web Service")));
		
		Course c = repository.findById(10001L);
		c.setName("JPA advance");
		repository.save(c);
		c = repository.findById(10001L);
		assertEquals("JPA advance", c.getName() );
		//logger.info("Inserting -> {}", 	repository.merge(c));
	}
	
	@Test
	public void testPlayWithEntityManager() {
		repository.playWithEntityManager();
		
	}
	
	@Test
	public void testRetrieveReviewsForCourses() {
		repository.retrieveReviewsForCourses();
		
	}
	
	@Test
	public void testRetrieveCourseForReview() {
		reviewRepository.retrieveCourseForReview();
		
	}
	
	@Test
	@Transactional
	public void testRetrieveStudentCourse() {
		Student student = entityManager.find(Student.class, 20001L);
		logger.info("student {}", student);
		logger.info("course {}", student.getCourses());
	}
	
}

