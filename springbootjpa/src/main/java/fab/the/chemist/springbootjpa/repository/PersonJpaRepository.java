package fab.the.chemist.springbootjpa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fab.the.chemist.springbootjpa.entity.Person;
import javassist.bytecode.stackmap.TypeData;

//l'annotation transactional permet d'effectue la persistence en DB ou rollback automatique en cas de problème
//si l'annotation manque alors rien ne se passera en DB

@Repository
@Transactional
public class PersonJpaRepository {
	
	//connection à la DB s'effectue avec entitymanager
	//il gère les entities, c'est a dire toute les opération qui sont effectuée dessus (CRUD)
	@PersistenceContext
	EntityManager entityManager;
	
	/**
	 * 
	 * @return
	 */
	public List<Person> findAll() {
		TypedQuery<Person> namedQuery = entityManager.createNamedQuery("find_all_persons", Person.class);
		return namedQuery.getResultList();
	}

	public Person findById(int id){
		return entityManager.find(Person.class, id);
	}
	
	/**
	 * merge est une methode comme en pl sql , on injecte une person et si elle est présente entitymanager 
	 * effectue une mise à jour , sinon entitymanager effectue un insert
	 * si on met le sql display à true, on voit que hibernate effectue un select si l'enregistrements s'y trouve il effectue un update
	 * sinon il effectue un insert 
	 * @param person
	 * @return
	 */
	public Person merge(Person person){
		return entityManager.merge(person);
	}

	public void deletebyId(int id){
		Person person = findById(id);
		entityManager.remove(person);
	}
	
}
