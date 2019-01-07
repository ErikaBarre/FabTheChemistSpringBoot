package fab.the.chemist.springbootaop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

//A tester avec beforeaspect2
//mettre en commentaire les annotation @Aspect et @Configuration dans autres classe
//decommenter les memes annotation dans beforeaspect2 et dans CommonJoinPointConfig

//@Aspect
//@Configuration
public class CommonJoinPointConfig {

	//on va intercepter toute les methods dans le package data
	@Pointcut("execution(* fab.the.chemist.springbootaop.data.*.*(..))")
	public void dataLayerExecution(){}

	@Pointcut("execution(* fab.the.chemist.springbootaop.business.*.*(..))")
	public void businessLayerExecution(){}

	
	//ensuite on reprend la classe contenant la methode avec l'annotation @Before
	// et on definit un autre paramètre plus generic faisant reference à la classe pointcut

	//@Before(fab.the.chemist.springbootaop.aspectCommonJoinPointConfig.dataLayerExecution())
	//et on fait de meme dans toutes les annotation, ainsi on a un point central pour définir les différent package sans
	//devoir chercher dans le code.

	
	//combinaise de pointcut, dont on lieur de faire un before ou un after sur 2 méthode : , 
	//@Before("fab.the.chemist.springbootaop.aspect.CommonJoinPointConfig.dataLayerExecution()") ....
	//@Before("fab.the.chemist.springbootaop.aspect.CommonJoinPointConfig.businessLayerExecution()") ....
	//on peut le faire sur une methode 
	//@Before("fab.the.chemist.springbootaop.aspect.CommonJoinPointConfig.allLayerExecution()") ....
	@Pointcut("fab.the.chemist.springbootaop.aspect.CommonJoinPointConfig.dataLayerExecution() && fab.the.chemist.springbootaop.aspect.CommonJoinPointConfig.businessLayerExecution()")
	public void allLayerExecution(){}
	
	
	@Pointcut("bean(Dao*)")  // s'applique à toute les classe commençant par Dao
	public void beanstartingwithdao(){}

	@Pointcut("bean(*Dao*)")  // s'applique à toute les classe contenant Dao
	public void beancontainingwithdao(){}
	
	//très similaire à la methode ci dessus dataLayerExecution
	@Pointcut("within(fab.the.chemist.springbootaop.data..*)")  
	public void beanwithin(){}
	
	
	@Pointcut("@annotation(fab.the.chemist.springbootaop.aspect.TrackTime)")
	public void trackTimeAnnotation() {} 
	
}
