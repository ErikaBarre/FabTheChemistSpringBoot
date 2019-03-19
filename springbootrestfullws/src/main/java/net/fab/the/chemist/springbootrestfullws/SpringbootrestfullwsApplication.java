package net.fab.the.chemist.springbootrestfullws;

import java.util.Locale;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class SpringbootrestfullwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootrestfullwsApplication.class, args);
	}

	//internationalization
	@Bean
	public SessionLocaleResolver localResolver() {
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(Locale.US);
		return sessionLocaleResolver;		
		
	}
	
	@Bean 
	public ResourceBundleMessageSource	messageSource() {
		ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
		resourceBundleMessageSource.addBasenames("messages");
		return resourceBundleMessageSource ; 
		
	}
	
}
