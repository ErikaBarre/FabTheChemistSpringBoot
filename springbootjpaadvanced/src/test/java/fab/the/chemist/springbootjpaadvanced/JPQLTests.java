package fab.the.chemist.springbootjpaadvanced;

/**
 * doc :
 * https://www.objectdb.com/java/jpa/query
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class JPQLTests {

	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository repository;
	
	@Autowired
	EntityManager entityManager;
	
	//@Test
	public void testFindById0() {
		// !!!! en jpql : la table en sql correspond a l'identifiant de la classe en respectant la casse (ici "Course")
		List<Course> listresult = entityManager.createQuery("select c from Course c").getResultList()	;
			
		for(Course c : listresult) {
			logger.info("{}", c.getName());
		}
		
		assertTrue(true);
	}
	
	//@Test
	public void testJpql() {
		//List<Course> listresult = entityManager.createQuery("select c from Course where name like 'JPA'", Course.class).getResultList();
		TypedQuery<Course> listresult = entityManager.createQuery("select c from Course c", Course.class);
		List<Course> lc = listresult.getResultList();
		for(Course c : lc) {
			logger.info("Jpql {}", c.getName());
		}		
		
		
		assertTrue(true);
	}

	//@Test
	public void testJpqlNamesQuery() {
		//List<Course> listresult = entityManager.createQuery("select c from Course where name like 'JPA'", Course.class).getResultList();
		TypedQuery<Course> listresult = entityManager.createNamedQuery("get_all_course", Course.class);
		List<Course> lc = listresult.getResultList();
		for(Course c : lc) {
			logger.info("JpqlNamesQuery( {}", c.getName());
		}		
		
		
		assertTrue(true);
	}
	
	//@Test
	public void testFindByIdp() {
		//List<Course> listresult = entityManager.createQuery("select c from Course where name like 'JPA'", Course.class).getResultList();
		TypedQuery<Course> listresult = entityManager.createQuery("select c from Course c where name like 'JPA'", Course.class);
		List<Course> lc = listresult.getResultList();
		for(Course c : lc) {
			logger.info("{}", c.getName());
		}		
		
		
		assertTrue(true);
	}
	
	//@Test
	public void testJpsqlNamesQueries() {
		TypedQuery<Course> listresult = entityManager.createNamedQuery("get_all_course_by_name", Course.class);
		List<Course> lc = listresult.getResultList();
		for(Course c : lc) {
			logger.info("{}", c.getName());
		}		
		
		
		assertTrue(true);
	}
	
	//@Test
	public void testJpsqlCoursesWithoutStudent() {
		TypedQuery<Course> listresult = entityManager.createQuery("select c from Course c where c.students is empty",Course.class);	
		
		List<Course> lc = listresult.getResultList();
		for(Course c : lc) {
			logger.info("{}", c.getName());
		}		
		
		
		assertTrue(true);
	}
	
	//@Test
	public void testJpsqlCoursesWithStudents() {
		TypedQuery<Course> listresult = entityManager.createQuery("select c from Course c where size(c.students) > 1",Course.class);	
		List<Course> lc = listresult.getResultList();
		for(Course c : lc) {
			logger.info("{}", c.getName());
		}		
			
		assertTrue(true);
	}
	
	//@Test
	public void testJpsqlCoursesOrderBy() {
		//TypedQuery<Course> listresult = entityManager.createQuery("select c from Course c order by id",Course.class);	
		TypedQuery<Course> listresult = entityManager.createQuery("select c from Course c order by size(c.students) desc",Course.class);	
		List<Course> lc = listresult.getResultList();
		for(Course c : lc) {
			logger.info("result ** {}", c.getName());
		}		
			
		assertTrue(true);
	}

	//@Test
	public void testJpsqlStudentWithPassportWithPattern() {
		//TypedQuery<Course> listresult = entityManager.createQuery("select c from Course c order by id",Course.class);	
		TypedQuery<Student> listresult = entityManager.createQuery("select s from Student s where s.passport.number like '%1234%'",Student.class);	
		List<Student> lc = listresult.getResultList();
		for(Student c : lc) {
			logger.info("result ** {}", c.getName());
		}		
			
		assertTrue(true);
	}
	
	//@Test
	public void testJpsqlStudentWithPassportWithBetween() {
		//TypedQuery<Course> listresult = entityManager.createQuery("select c from Course c order by id",Course.class);	
		TypedQuery<Student> listresult = entityManager.createQuery("select s from Student s where s.passport.id between 10001 and 10010",Student.class);	
		List<Student> lc = listresult.getResultList();
		for(Student c : lc) {
			logger.info("result ** {}", c.getName());
		}		
			
		assertTrue(true);
	}
	
	//@Test
	public void testJpsqlStudentWithPassportWithUIsNull() {
		TypedQuery<Student> listresult = entityManager.createQuery("select s from Student s where s.passport.number is null",Student.class);	
		List<Student> lc = listresult.getResultList();
		for(Student c : lc) {
			logger.info("result ** {}", c.getName());
		}		
			
		assertTrue(true);
	}
	
	//trim  upper lower length
	//@Test
	public void testJpsqlStudentWithPassportWithTRIM() {
		TypedQuery<Student> listresult = entityManager.createQuery("select s from Student s where trim(s.name) like 'Jack'",Student.class);	
		List<Student> lc = listresult.getResultList();
		for(Student c : lc) {
			logger.info("result ** {}", c.getName());
		}		
			
		assertTrue(true);
	}
	
	/**
	 * correspondant à un inner join
	 */
	//@Test
	public void testJoin() {
		Query query = entityManager.createQuery("select c, s from Course c Join c.students s");
		List<Object[]> resultList = query.getResultList();
		//le resultat est un array (les éléments se trouvent dans un array d'array)
		for(Object[] result : resultList) {
			logger.info("course {}" , result[0]); //course
			logger.info("student {}" , result[1]); //student
		}
		
		assertTrue(true);
	}
	
	/**
	 * correspond à un left outer join
	 * ramène les cours qui n'ont pas d'étudiants
	 */
	@Test
	public void testLeftJoin() {
		Query query =  entityManager.createQuery("select c, s from Course c LEFT JOIN c.students s");		
		List<Object[]> resultList = query.getResultList();
		//le resultat est un array (les éléments se trouvent dans un array d'array)
		for(Object[] result : resultList) {
			logger.info("course LJ {}" , result[0]); //course
			logger.info("student LJ {}" , result[1]); //student
			
			List<Course> listc = new ArrayList(Arrays.asList(result[0]));
			List<Course> lists = new ArrayList(Arrays.asList(result[1]));//????? renvoit un tableau de student
			
			logger.info("end");
			
		}
		assertTrue(true);
	}
	
	/**
	 * correspond à un cross join
	 */
	//@Test
	public void testCrossJoin() {
		Query query = entityManager.createQuery("select c, s from Course c, Student s");		
		List<Object[]> resultList = query.getResultList();
		//le resultat est un array (les éléments se trouvent dans un array d'array)
		for(Object[] result : resultList) {
			logger.info("course {}" , result[0]); //course
			logger.info("student {}" , result[1]); //student
		}
		
		assertTrue(true);
	}
	
}

