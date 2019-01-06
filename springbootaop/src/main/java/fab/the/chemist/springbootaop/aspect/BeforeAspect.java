package fab.the.chemist.springbootaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class BeforeAspect { 
	//fab.the.chemist.springbootaop.business.Business.calculateSomething()
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
//le but est d'intercepter l'exécution d'un méthode
	//la methode ici va se déclancher avant chaque déroulement d'une méthode d'une classe du package précisé dans execution de l'annotation before
	//execution(* fab.the.chemist.springbootaop.business.Business.*.*(..))
	@Before("execution(* fab.the.chemist.springbootaop.business.*.*(..))")
	//public void before() {
	public void before(JoinPoint joinpoint) {
		//whet to do ?
		LOGGER.info("intercept method call {}", joinpoint);
	}
	
}
