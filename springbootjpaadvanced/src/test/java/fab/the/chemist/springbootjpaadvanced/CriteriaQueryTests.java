package fab.the.chemist.springbootjpaadvanced;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fab.the.chemist.springbootjpaadvanced.entity.Course;
import fab.the.chemist.springbootjpaadvanced.repository.CourseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CriteriaQueryTests {

	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository repository;
	
	@Autowired
	EntityManager entityManager;
	
	@Test
	public void testFindAllCourses() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> cq = criteriaBuilder.createQuery(Course.class);
		Root<Course> courseRoot =cq.from(Course.class);
		TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
		
		List<Course> courses = query.getResultList();
		
		for(Course course: courses) {
			logger.info("course name {}", course.getName());
		}
		
		assertTrue(true);
	}
	
	@Test
	public void testFindWhereClause() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> cq = criteriaBuilder.createQuery(Course.class);
		Root<Course> courseRoot =cq.from(Course.class);
		CriteriaQuery<Course> select = cq.select(courseRoot);
		Predicate like = criteriaBuilder.like(courseRoot.get("name"), "JPA");
		cq.where(like);
		TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
		
		List<Course> courses = query.getResultList();
		
		for(Course course: courses) {
			logger.info("course name 2 {}", course.getName());
		}
		
		assertTrue(true);
	}
	
	@Test
	public void testFindCourseWithoutStudent() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
		Root<Course> courseRoot =criteriaQuery.from(Course.class);
		criteriaQuery.select(courseRoot);
		Predicate empty = criteriaBuilder.isEmpty(courseRoot.get("students"));
		criteriaQuery.where(empty);
		TypedQuery<Course> query = entityManager.createQuery(criteriaQuery.select(courseRoot));
		
		List<Course> courses = query.getResultList();
		
		for(Course course: courses) {
			logger.info("course name 2 {}", course.getName());
		}
		
		assertTrue(true);
	}
	
	@Test
	public void testJoin() {
		//1. criteria builder
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
		
		//2. root
		Root<Course> courseRoot =criteriaQuery.from(Course.class);
		
		//3. predicate avec criteriabuilder
		//inner join
		//courseRoot.join("students");
		//left join
		courseRoot.join("students", JoinType.LEFT);
		criteriaQuery.select(courseRoot);
		
		//4. preicate avec CriteriaQuery
		
		
		//5. typed query
		TypedQuery<Course> query = entityManager.createQuery(criteriaQuery.select(courseRoot));
		
		List<Course> courses = query.getResultList();
		
		for(Course course: courses) {
			logger.info("join course name 2 {}", course.getName());
		}
		
		assertTrue(true);
	}
	

	
}

