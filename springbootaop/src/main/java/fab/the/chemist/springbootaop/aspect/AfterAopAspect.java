package fab.the.chemist.springbootaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

//@Aspect
//@Configuration
public class AfterAopAspect {

	Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	//la methode incepte également les méthode du package definit comme argument dans l'annotation. 
	//Et s'exécute après les methode interceptee pour ramene la valuer retourné par les methodes interceptées
	@AfterReturning(
			value="execution(* fab.the.chemist.springbootaop.business.*.*(..))",
			returning="result"  //l'dentifiant doit mapper un parametre de la methode
			)
	public void afterReturning(JoinPoint jointPoint, Object result){
		LOGGER.info("{} returned with value {}", jointPoint, result  );
	}

	//ramène les exceptions éventuellement lancées,  des methodes interceptées
	@AfterThrowing(
			value="execution(* fab.the.chemist.springbootaop.business.*.*(..))",
			throwing="exception"  //le type d'exception à intercepter
			)
	public void afterThrowing(JoinPoint jointPoint, Object result){
		LOGGER.info("{} returned with exception {}", jointPoint, result  );
	}

	//se déclenche après toutes les methodes interceptée du package passé en paramètre
	@After(
			value="execution(* fab.the.chemist.springbootaop.business.*.*(..))"
			)
	public void after(JoinPoint jointPoint){
		LOGGER.info("after execution {} ", jointPoint);
	}

}
