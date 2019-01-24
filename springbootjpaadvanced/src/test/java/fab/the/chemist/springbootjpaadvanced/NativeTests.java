package fab.the.chemist.springbootjpaadvanced;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
import fab.the.chemist.springbootjpaadvanced.repository.CourseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NativeTests {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository repository;
	
	@Autowired
	EntityManager entityManager;
		
	@Test
	public void testNative() {
		Query query = entityManager.createNativeQuery("select * from fab_course");
		List lc = query.getResultList();
		
		logger.info("{}",lc);
		
//		for(Course c : lc) {
//			logger.info("native {}", c.toString()());
//		}		
		
		
		assertTrue(true);
	}
	
	
	//les query native n'utilise pas les annotations du softdelete, il faut ajoute une clause where à la requete
	//ici 0 signifie "false"
	@Test
	public void testDeletedNative() {
		Query query = entityManager.createNativeQuery("select * from fab_course where co_id_deleted=0");
		List lc = query.getResultList();
		
		logger.info("{}",lc);
		
//		for(Course c : lc) {
//			logger.info("native {}", c.toString()());
//		}		
		
		
		assertTrue(true);
	}


	
	@Test
	public void testNativeType() {
		Query query = entityManager.createNativeQuery("select * from fab_course", Course.class);
		List<Course> lc = query.getResultList();
		for(Course c : lc) {
			logger.info("native {}", c.getName());
		}		
		
		
		assertTrue(true);
	}

	@Test
	public void testNativeTypeParameterNumber() {
		Query query = entityManager.createNativeQuery("select * from fab_course where co_id = ?", Course.class);
		query.setParameter(1, 10001L);	
		List<Course> lc = query.getResultList();
		for(Course c : lc) {
			logger.info("native {}", c.getName());
		}		
		
		
		assertTrue(true);
	}
	
	@Test
	public void testNativeTypeParameterNumber2() {
		Query query = entityManager.createNativeQuery("select * from fab_course where co_id = :id", Course.class);
		query.setParameter("id", 10001L);	
		List<Course> lc = query.getResultList();
		for(Course c : lc) {
			logger.info("native {}", c.getName());
		}		
		
		
		assertTrue(true);
	}
	
	@Test
	public void testNativeTypeParameterString() {
		Query query = entityManager.createNativeQuery("select * from fab_course where co_name = ?", Course.class);
		query.setParameter(1, "JPA");	
		List<Course> lc = query.getResultList();
		for(Course c : lc) {
			logger.info("native {}", c.getName());
		}		
		
		
		assertTrue(true);
	}
	
	//pas oublier l'annotation transactional car on exécute directement dans le test unitaire
	@Test
	@Transactional
	public void testNativeTypeParameterStringUpdate() {
		Query query = entityManager.createNativeQuery("update fab_course set last_updated_date = sysdate()", Course.class);
		
		int lc = query.executeUpdate();
		logger.info("update native {}", lc);
		assertTrue(true);
	}
}

