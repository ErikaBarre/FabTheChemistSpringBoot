package fab.the.chemist.springbootjpa;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fab.the.chemist.springbootjpa.bean.Person;
import fab.the.chemist.springbootjpa.dao.PersonJdbcDao;


//@SpringBootApplication
public class SpringbootJDBCApplication implements CommandLineRunner{

	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	PersonJdbcDao dao;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootJDBCApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		List<Person> plist =  dao.findAll();
//		for(Person p : plist) {
//			LOGGER.info(p.getName());
//		}
		LOGGER.info("1. {}", dao.findAllBasic());
		LOGGER.info("2. {}", dao.findAll());
		LOGGER.info("select {}", dao.findById(10001));
		LOGGER.info("insert {}", dao.insert(new Person(10010, "Jane", "Amsterdam", new Date() )));
		LOGGER.info("select {}", dao.findById(10010));
		//LOGGER.info("delete {}", dao.deleteById(10010));
		LOGGER.info("select {}", dao.findById(10002));
		LOGGER.info("update {}", dao.updateById(new Person(10002, "Janette", "Amsterdam", new Date() )));
		LOGGER.info("select {}", dao.findById(10002));
		
		//dao.commit();
		
		/*
		
		
		
		LOGGER.info("{}", dao.updateById(new Person(10010, "Janette", "Amsterdam", new Date() )));
	*/
	}

}

