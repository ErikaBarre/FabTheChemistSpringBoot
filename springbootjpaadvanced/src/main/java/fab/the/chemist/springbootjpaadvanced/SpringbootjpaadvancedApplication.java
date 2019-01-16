package fab.the.chemist.springbootjpaadvanced;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fab.the.chemist.springbootjpaadvanced.entity.Course;
import fab.the.chemist.springbootjpaadvanced.entity.Employee;
import fab.the.chemist.springbootjpaadvanced.entity.FullTimeEmployee;
import fab.the.chemist.springbootjpaadvanced.entity.PartTimeEmployee;
import fab.the.chemist.springbootjpaadvanced.entity.Review;
import fab.the.chemist.springbootjpaadvanced.entity.Student;
import fab.the.chemist.springbootjpaadvanced.repository.CourseRepository;
import fab.the.chemist.springbootjpaadvanced.repository.EmployeeRepository;
import fab.the.chemist.springbootjpaadvanced.repository.StudentRepository;
import net.bytebuddy.implementation.bind.annotation.Empty;

@SpringBootApplication
public class SpringbootjpaadvancedApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository repository;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootjpaadvancedApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//changer les propriétés dans application properties
		//on se rend compte que spring vont qu'on utilise h2 et il génére tout car il est autoconfigurer
		// il drop table, drop schema, drop sequence et recrée tout table schéma sequance , insertion d'enristrement 
		//sur base de la définition de l'entite (dedans on y trouve nom table, colonne) et data.sql pour les données.  
		
		/*
		logger.info("User id 10001 -> {}", repository.findById(10001L));
		logger.info("User id 10002 -> {}", repository.findById(10002L));
		repository.deleteById(10002L);
		logger.info("User id 10002 -> {}", repository.findById(10002L));
		
		logger.info("Inserting -> {}", 	repository.save(new Course("Web Service")));
		Course c = repository.findById(10001L);
		c.setName("JPA advance");
		logger.info("Inserting -> {}", 	repository.save(c));
		*/
		
		//repository.playWithEntityManager();
		//repository.playWithEntityManagerDetach();
		//repository.playWithEntityManagerRefresh();
		
		//studentRepository.saveStudentWithPassport("Luke", "DD78856");
		/*
		List<Review> reviews = new ArrayList<Review>();
		reviews.add(new Review("4", "mega very nice review"));
		reviews.add(new Review("5", "awsome dude"));
			
		repository.addReviewsForCourse(10001L,reviews);
		*/
		/*
		studentRepository.insertStudentInCourse();
		studentRepository.insertStudentInCourse(new Student("Jack"), new Course("Microservices strategy"));
		*/
		
		//inheritance
		/*
		employeeRepository.insert(new FullTimeEmployee("Natalie", new BigDecimal(10000)));
		employeeRepository.insert(new PartTimeEmployee("Julie", new BigDecimal(50)));
		
		List<Employee> employees = employeeRepository.findAll();
		logger.info("employees -> {}", 	employees);
		*/
		
		
	}

}

