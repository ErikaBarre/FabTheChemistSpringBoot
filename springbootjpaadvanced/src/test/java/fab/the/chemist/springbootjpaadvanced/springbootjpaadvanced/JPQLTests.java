package fab.the.chemist.springbootjpaadvanced.springbootjpaadvanced;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import fab.the.chemist.springbootjpaadvanced.springbootjpaadvanced.entity.Course;
import fab.the.chemist.springbootjpaadvanced.springbootjpaadvanced.repository.CourseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JPQLTests {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository repository;
	
	@Autowired
	EntityManager entityManager;
	
	@Test
	public void testFindById0() {
		List<Course> listresult = entityManager.createQuery("select c from Course c").getResultList()	;
		
		
		for(Course c : listresult) {
			logger.info("{}", c.getName());
		}
		
		assertTrue(true);
	}
	
	@Test
	public void testFindById() {
		//List<Course> listresult = entityManager.createQuery("select c from Course where name like 'JPA'", Course.class).getResultList();
		TypedQuery<Course> listresult = entityManager.createQuery("select c from Course c", Course.class);
		List<Course> lc = listresult.getResultList();
		for(Course c : lc) {
			logger.info("{}", c.getName());
		}		
		
		
		assertTrue(true);
	}

	@Test
	public void testFindByIdp() {
		//List<Course> listresult = entityManager.createQuery("select c from Course where name like 'JPA'", Course.class).getResultList();
		TypedQuery<Course> listresult = entityManager.createQuery("select c from Course c where name like 'JPA'", Course.class);
		List<Course> lc = listresult.getResultList();
		for(Course c : lc) {
			logger.info("{}", c.getName());
		}		
		
		
		assertTrue(true);
	}
	
	
}

