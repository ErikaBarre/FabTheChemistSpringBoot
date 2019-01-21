package fab.the.chemist.springbootjpaadvanced;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.context.junit4.SpringRunner;

import fab.the.chemist.springbootjpaadvanced.entity.Course;
import fab.the.chemist.springbootjpaadvanced.entity.CourseSpringDataRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseSpringDataRepository courseSpringDataRepository;
	
	@Autowired
	EntityManager entityManager;
	
	@Test
	public void testFindById() {
		Optional<Course> course = courseSpringDataRepository.findById(10001L);
		logger.info("COURSE PRESENT{}", course.isPresent());
		assertTrue(true)	;
	}

	//en utalisant l'interfac on cosntate que la meme méthode save
	//effectue une insertyion en base de donnéess et aussi une mise à jour
	@Test
	public void saveCourse(){
		Course course = new Course("Microservices");
		courseSpringDataRepository.save(course);
		course.setName("Microservices-update");
		courseSpringDataRepository.save(course);

	}

	@Test
	public void findAll(){
	logger.info("all courses : {}" , courseSpringDataRepository.findAll());
	logger.info("count courses {} " , courseSpringDataRepository.count());
	}

	@Test
	public void sorting(){
	Sort sort = new Sort(Direction.DESC,"name");
	logger.info("sorted all courses : {}" , courseSpringDataRepository.findAll(sort));

	//si on veut 2 critere de tri
	Sort sort2 = new Sort(Direction.ASC,"name"). and(sort);
	logger.info("sorted all courses : {}" , courseSpringDataRepository.findAll(sort));
	}
	
	//ramener les data par par paquet d'un nombre fixe
	@Test
	public void pagination(){
	PageRequest pageRequest = PageRequest.of(0, 3);
	Page<Course> firstPage = courseSpringDataRepository.findAll(pageRequest );
	logger.info("first page {}", firstPage);
	firstPage.getNumber(); //pour connaitre le numero de la page ramenée : 0 est la 1er page
	Pageable nextPage = firstPage.nextPageable();  // page suivante , à utiliser avec "hasNext" pour vérifier que la page n'est pas null
	Page<Course> secondPage = courseSpringDataRepository.findAll(nextPage);
	}


	@Test
	public void findUsingName(){
	    logger.info("findbyname {}", courseSpringDataRepository.findByName("JPA"));

	}
	
	@Test
	
	public void testOther() {

		//springdatareconnait le mot find ,on le suivre par by+nom du champs et spring data retrouve ses jeunes
		//avec "query " ça fonctionne aussi (??? à tester)
		//avec count aussi
		List<Course> courses = courseSpringDataRepository.findByName("JPA");
		List<Course> courses2 = courseSpringDataRepository.queryByName("JPA");
		List<Course> course = courseSpringDataRepository.countByName("JPA");
		//aussi multiple champs
		List<Course> courses3 = courseSpringDataRepository.findByNameAndId ("JPA", 10001L);
		//tri
		List<Course> courses31 = courseSpringDataRepository.findByNameOrderByIdDesc("JPA");

		List<Course> courses5 = courseSpringDataRepository.coursesWithJPAInIt();


		List<Course> courses7 = courseSpringDataRepository.coursesWithJPAInItNative();

		List<Course> course8 = courseSpringDataRepository.coursesWithJPAInItWithIdentifierQuery();
			
		//idem pour save delete update
		//@Transactional
		courseSpringDataRepository.deleteByName("Spring BooT");
		
	}
	
}
