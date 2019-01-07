package fab.the.chemist.springbootaop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

//à tester avec commonjointpointsconfig.java

@Aspect
@Configuration
public class MethodExcecutionCalculationAspect2 {

	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	//ProceedingJoinPoint
	//permet de calculer le temps que mettent à s'exécuter les methodes interceptée
	//il va aller chercher la methode CommonJoinPointConfig.trackTimeAnnotation() et exécuter le business de la methode uniquement 
	//sur les methode qui ont l'annotation correspondante 
	@Around("fab.the.chemist.springbootaop.aspect.CommonJoinPointConfig.trackTimeAnnotation()")
	public void around(ProceedingJoinPoint jointPoint) throws Throwable{
		long startTime = System.currentTimeMillis() ;
		jointPoint.proceed();
		long timeTaken = System.currentTimeMillis() - startTime;
		LOGGER.info("time ** taken by {} is {} milli", jointPoint, timeTaken);
	}

	
}
