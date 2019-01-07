package fab.the.chemist.springbootaop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class MethodExcecutionCalculationAspect {

	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	//ProceedingJoinPoint
	//permet de calculer le temps que mettent à s'exécuter les methodes interceptée
	@Around(
	"execution(* fab.the.chemist.springbootaop.business.*.*(..))"
	)
	public void around(ProceedingJoinPoint jointPoint) throws Throwable{
	long startTime = System.currentTimeMillis() ;
	jointPoint.proceed();
	long timeTaken = System.currentTimeMillis()
	- startTime;
	LOGGER.info("time taken by {} is {}", jointPoint, timeTaken);
	}

	
}
