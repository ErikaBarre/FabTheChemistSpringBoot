package fab.the.chemist.springbootaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

//@Aspect
//@Configuration
public class BeforeAspect { 
	//fab.the.chemist.springbootaop.business.Business.calculateSomething()
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
//le but est d'intercepter l'exécution d'un méthode
	//la methode ici va se déclancher avant chaque déroulement d'une méthode d'une classe du package précisé dans execution de l'annotation before
	//execution(* fab.the.chemist.springbootaop.business.Business.*.*(..))
	//@Before("execution(* fab.the.chemist.springbootaop.business.*.*(..))")
	@Before("execution(* fab.the.chemist.springbootaop..*.*(..))")
	//public void before() {
	public void before(JoinPoint joinpoint) {
		//whet to do ?
		LOGGER.info("intercept method call {}", joinpoint);
	}
	
	//@Before("execution(* fab.the.chemist.springbootaop.business.*.*(..))")
	//@Before("execution(* fab.the.chemist.springbootaop.data.*.*(..))")
	//on peut intercepter les sous package en modifiant le path en écrivant notamment
	//dans ce cas, on intercepte aussi les méthode des classes Dao et Dao2
	
	
}
//"pointcut" : c'est ce qui se trouve entre parenthèse du before
//le contenu de la methode qui supporte l'annotation before est "advice"
//la combinaison entre le pointcut et advice est ce qu'on appelle l'aspect
//jointpoitn est l'interception spécifique entre les appels aux methodes
//il y a autant de joinpoint que d'interception
//weaving and weaver: l'interception des methode est le weaving et le framework en charge de l'interception est le weaver