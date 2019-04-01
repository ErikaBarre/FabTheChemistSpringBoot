package net.fab.the.chemist.springbootrestfullws;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	public static final Contact DEFAULT_CONTACT = new Contact("fabthechemist sarl", "http://www.fabthechemist.com", "fabthechemist@gmail.com");
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
										"Restful WS", 
										"Resful wb Documentation", 
										"1.0", 
										"urn:tos",
										DEFAULT_CONTACT, 
										"Apache 2.0", 
										"http://www.apache.org/licenses/LICENSE-2.0", 
										new ArrayList<VendorExtension>());
	private static final Set<String> PRODUCES = new HashSet<String>(Arrays.asList("application/json"));
	private static final Set<String> CONSUMES = new HashSet<String>(Arrays.asList("application/json"));

	//afficher la documentation des service 
	//http://localhost:8080/v2/api-docs
	//http://localhost:8080/swagger-ui.html
	@Bean
	public Docket api() {
		
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT_API_INFO)
				.produces(PRODUCES)
				.consumes(CONSUMES)
				
				;
	}

}
