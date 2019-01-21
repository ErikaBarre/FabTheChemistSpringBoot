package fab.the.chemist.springbootjpaadvanced.entity;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="/course")
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {

	
	public List<Course> findByName(String name);
	
	//springdatareconnait le mot find ,on le suivre par by+nom du champs et spring data retrouve ses jeunes
	//avec "query " ça fonctionne aussi (??? à tester)
	//avec count aussi
	
	List<Course> queryByName(String name);
	List<Course> countByName(String name);
	//aussi multiple champs
	List<Course> findByNameAndId (String name, Long id);
	//tri
	List<Course> findByNameOrderByIdDesc(String Name);

	//idem pour save delete update

	void deleteByName(String name);

	//pour une query sur mesure
	@Query("Select c from Course c where name like 'JPA'")
	List<Course> coursesWithJPAInIt();

	//avec une native query
	//@Query("Select * from fab_course where co_name like 'JPA'", nativeQuery=true)
	@Query(value = "Select * from FAB_COURSE where CO_NAME like 'JPA'", nativeQuery=true)
	List<Course> coursesWithJPAInItNative();

	//avec un identifiant
	@Query(name="get_all_course_by_name")
	List<Course> coursesWithJPAInItWithIdentifierQuery();

	
	
}
