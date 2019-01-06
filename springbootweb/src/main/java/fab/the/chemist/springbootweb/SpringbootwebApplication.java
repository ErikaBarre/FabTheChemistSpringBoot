package fab.the.chemist.springbootweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringbootwebApplication {

	public static void main(String[] args) {
		//SpringApplication.run(SpringbootwebApplication.class, args);
		
		ApplicationContext applicationContext = SpringApplication.run(SpringbootwebApplication.class, args);
		
		for(String name: applicationContext.getBeanDefinitionNames()) {
			System.out.println(name);
		}
		
	}

}

