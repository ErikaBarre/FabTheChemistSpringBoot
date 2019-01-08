package fab.the.chemist.springbootjpa;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fab.the.chemist.springbootjpa.dao.PersonJdbcDao;
import fab.the.chemist.springbootjpa.orm.Person;

@SpringBootApplication
public class SpringbootjpaApplication implements CommandLineRunner{

	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	PersonJdbcDao dao;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootjpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Person> plist =  dao.findAll();
		for(Person p : plist) {
			LOGGER.info(p.getName());
		}
		LOGGER.info("{}", dao.findAll());
		LOGGER.info("{}", dao.findById(10001));
	
	}

}

