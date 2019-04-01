package net.fab.the.chemist.springbootrestfullws;

import java.util.Locale;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties.LocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

//  http://localhost:8080/actuator

@SpringBootApplication
public class SpringbootrestfullwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootrestfullwsApplication.class, args);
	}

	//internationalization
//	@Bean
//	public SessionLocaleResolver localeResolver() {
//		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
//		sessionLocaleResolver.setDefaultLocale(Locale.US);  //ne fonctionne pas, va prendre le local du PC
//		return sessionLocaleResolver;		
//		
//	}
	
	@Bean
	public AcceptHeaderLocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver sessionLocaleResolver = new AcceptHeaderLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(Locale.US);  //ne fonctionne pas, va prendre le local du PC
		return sessionLocaleResolver;		
		
	}
	
//	@Bean 
//	public ResourceBundleMessageSource	messageSource() {
//		ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
//		resourceBundleMessageSource.addBasenames("messages");
//		return resourceBundleMessageSource ; 
//		
//	}  //=> peut etre remplacé par une propriété dans le fichier application.properties
	
}
