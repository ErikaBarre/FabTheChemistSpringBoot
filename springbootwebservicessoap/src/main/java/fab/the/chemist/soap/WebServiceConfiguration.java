package fab.the.chemist.soap;

import javax.servlet.Servlet;
import javax.servlet.ServletRegistration;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

//permet l'imporation des web service
@EnableWs
//spring configuraition
@Configuration
public class WebServiceConfiguration {

	//on doit ensuite configurer un dispatcher comme pour une servlet
	//la servlet reçoit la demande et ensuite spring se charge d'utiliser le bon controller pour fournir l'info
	// en WS la servlet reçoit la demande et va retrouve le bon message de WS à utiliser, il va retrouve la endpoint
	// on va enregistrer une servlet en la définition
	//il faut mapper la servlet à uri
	
	@Bean
	public ServletRegistrationBean messageDistpacherServlet (ApplicationContext context){
		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
		messageDispatcherServlet.setApplicationContext(context);
		messageDispatcherServlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
	}
	
	@Bean(name="courses")
	public DefaultWsdl11Definition defaultWsdl11Definition (XsdSchema xsdSchema) {
		DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
		defaultWsdl11Definition.setPortTypeName("CoursePort");
		defaultWsdl11Definition.setSchema(xsdSchema);
		defaultWsdl11Definition.setLocationUri("/ws/");
		defaultWsdl11Definition.setTargetNamespace("http://fabthechemist.net/courses");
		return defaultWsdl11Definition;
	}
	
	@Bean
	public XsdSchema coursesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("course.xsd"));
	}
}
