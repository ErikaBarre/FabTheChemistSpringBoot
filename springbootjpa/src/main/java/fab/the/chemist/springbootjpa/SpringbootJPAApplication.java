package fab.the.chemist.springbootjpa;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fab.the.chemist.springbootjpa.entity.Person;
import fab.the.chemist.springbootjpa.repository.PersonJpaRepository;


//@SpringBootApplication
public class SpringbootJPAApplication implements CommandLineRunner{

	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	PersonJpaRepository dao;
	
	public static void main(String[] args) {
		//SpringApplication.run(SpringbootJPAApplication.class, args);
		 try {
			 	SpringApplication.run(SpringbootJPAApplication.class, args);
		    } catch (Exception ex) {
		    	throw new RuntimeException(ex);
		        //Throwable realCause = unwrap(ex);
		        // Perform action based on real cause
		    }
		
		
	}

	@Override
	public void run(String... args) throws Exception {

		LOGGER.info("JPA. {}", dao.findById(10001));
		LOGGER.info("JPA.update {}", dao.merge(new Person(10001, "Polux", "Pekin", new Date() )));
		LOGGER.info("JPA. {}", dao.findById(10001));
		LOGGER.info("JPA. {}", dao.findById(10020));
		LOGGER.info("JPA.insert {}", dao.merge(new Person(10020, "Gaby", "Helsinki", new Date() )));
		LOGGER.info("JPA. {}", dao.findById(10020));
		LOGGER.info("JPA. {}", dao.findById(10003));
		LOGGER.info("JPA delete ");
		dao.deletebyId(10003);//LOGGER.info("JPA delete ", dao.deletebyId(10003));
		LOGGER.info("JPA. {}", dao.findById(10003));
		
		LOGGER.info("JPA. {}", dao.findAll());
	}

}

