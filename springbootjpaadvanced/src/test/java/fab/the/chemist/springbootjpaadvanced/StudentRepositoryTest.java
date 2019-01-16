package fab.the.chemist.springbootjpaadvanced;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fab.the.chemist.springbootjpaadvanced.entity.Passport;
import fab.the.chemist.springbootjpaadvanced.entity.Student;
import fab.the.chemist.springbootjpaadvanced.repository.StudentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	EntityManager entityManager;
	
	@Test
	@Transactional //avec cette annotation on peut placer fetch.lazy sinon erreur ou sinon utiliser eager (mais c'est moins performant
	public void testRetriveStudentPassport() {
		Student student = entityManager.find(Student.class, 20001L);
		logger.info("Student {}", student);
		Passport passport = student.getPassport();
		logger.info("Passport {}", passport);
	}
	
	@Test
	@Transactional //avec cette annotation on peut placer fetch.lazy sinon erreur ou sinon utiliser eager (mais c'est moins performant
	public void testRetriveStudentPassportInverse() {
		Passport passport = entityManager.find(Passport.class, 30001L);
		logger.info("Student {}", passport);
		Student student = passport.getStudent();
		logger.info("Passport {}", student);
	}
	
	@Test
	@Transactional  //on cree un persistance context
	public void testRetriveStudentPassportUpdate() {
		Student student = entityManager.find(Student.class, 20001L);
		logger.info("Student {}", student);
		//ici le persistent context s'effectue sur student
		
		Passport passport = student.getPassport();
		logger.info("Passport {}", passport);
		//ici le persistent context s'effectue sur student et passport
		
		passport.setNumber("BP7854");
		//ici le persistent context s'effectue sur student et passport modifé
		
		student.setName("Rocco");
		//ici le persistent context s'effectue sur student modifié et passport modifié
	}

	@Test
	public void testRetriveStudentPassportUpdate_reporte_dans_la_dao() {
		//fonctionne sans l'annotation transactionnel car l'annotation se trouve fixée pour la classe dao
		studentRepository.retriveStudentPassportUpdate();
	}

	

	
}
