package fab.the.chemist.springbootaop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import fab.the.chemist.springbootaop.business.Business;
import fab.the.chemist.springbootaop.business.Business2;

@SpringBootApplication
public class SpringbootaopApplication implements CommandLineRunner {
	
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	Business business;
	
	@Autowired
	Business2 business2;
	
	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(SpringbootaopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		LOGGER.info(business2.calculateSomething());
		
		LOGGER.info(business.calculateSomething());
		
	}

}

