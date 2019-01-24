package fab.the.chemist.springbootjpaadvanced;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fab.the.chemist.springbootjpaadvanced.entity.Course;
import fab.the.chemist.springbootjpaadvanced.entity.Passport;
import fab.the.chemist.springbootjpaadvanced.entity.Student;
import fab.the.chemist.springbootjpaadvanced.repository.CourseRepository;
import fab.the.chemist.springbootjpaadvanced.repository.StudentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	EntityManager entityManager;
	
	@Test
	public void testSoftDelete() {
		List<Course> courses = courseRepository.findAll();
		logger.info("courses {} ", courses);
		//courseRepository.deleteById(10002L);
		
		courses = courseRepository.findAll();
		logger.info("courses {} ", courses);
		assertTrue(true);
	}

	

	
}
