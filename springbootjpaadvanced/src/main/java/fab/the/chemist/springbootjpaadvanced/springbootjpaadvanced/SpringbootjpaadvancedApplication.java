package fab.the.chemist.springbootjpaadvanced.springbootjpaadvanced;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fab.the.chemist.springbootjpaadvanced.springbootjpaadvanced.entity.Course;
import fab.the.chemist.springbootjpaadvanced.springbootjpaadvanced.repository.CourseRepository;

@SpringBootApplication
public class SpringbootjpaadvancedApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository repository;
	
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
		repository.playWithEntityManagerRefresh();
		
		

		
	}

}

