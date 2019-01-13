package fab.the.chemist.springbootjpaadvanced.springbootjpaadvanced.repository;

import javax.persistence.EntityManager;
//import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fab.the.chemist.springbootjpaadvanced.springbootjpaadvanced.entity.Passport;
import fab.the.chemist.springbootjpaadvanced.springbootjpaadvanced.entity.Student;

@Transactional
@Repository
public class StudentRepository {

	@Autowired
	EntityManager entityManager;
	
	@Autowired
	PassportRepository passportRepository;
	
	public Student findById(long id){
		return entityManager.find(Student.class, id);
	}
	
	public Student save(Student student){
		if(student.getId() == null) {
			entityManager.persist(student);
		}else {
			entityManager.merge(student);  // a noter que merge fait également un insert s'il ne trouve pas l'object
		}
		/*ces ligne de code sont equivalentes à cett§e ligne
		 entityManager.merge(student);		 
		 */
		
		return student;
	}
	
	public void saveStudentWithPassport(String name, String number) {
		Passport passport = new Passport(number);
		passportRepository.save(passport);		
		
		Student student = new Student(name, passport);
		save(student);
				
	}

	public void deleteById(long id){
		Student student = findById(id);
		entityManager.remove(student);
	}
	
	public void playWithEntityManager() {
		Student c = new Student("JMS");
		entityManager.persist(c);
		c.setName("ExtJs");	
		//on a un update car entitye manager continue à traqué l'object. 
		//la transaction n'est pas 
		//encore cloturée et il va faire un update sans devoir effectue un appel à la methode merge() 
	}
	
	public void playWithEntityManagerDetach() {
		Student c = new Student("Vanilla js");
		entityManager.persist(c);
		
		entityManager.flush(); // sert à effectuer une syncho avec la base de donnée
		entityManager.detach(c); // entity se détache de l'objet, l'object n'est plus lié , les mise à jour qui suive ne s'effectue plus
		c.setName("Angular js");	
				
		//si on écrit ceci sans flush alors le detach agit comme un rollback et rien n'est persisté en DB 
		Student c1 = new Student("Vanilla js ***");
		entityManager.persist(c1);
				
		entityManager.detach(c1); // entity se détache de l'objet, l'object n'est plus lié , les mise à jour qui suive ne s'effectue plus
		c1.setName("Angular js");
		
		//si on lie plusieur object à entioty manager
		Student c2 = new Student("Vanilla js");
		entityManager.persist(c2);
		
		Student c3 = new Student("Vanilla js");
		entityManager.persist(c3);
		
		entityManager.flush(); //pas oublie le flush sinon avec le clear rien n'est persisté en DB
		entityManager.clear(); //equivaut à détaché TOUS les object de l'entité manger en meme temps ( entityManager.detach(c1) entityManager.detach(c2) entityManager.detach(c3) ....)
		
		c2.setName("Angular js ++");
		c3.setName("Angular js +++");
		
		
		
		
	}
	
	public void playWithEntityManagerRefresh() {
		
		Student c2 = new Student("Vanilla js");
		entityManager.persist(c2);
		
		Student c3 = new Student("Vanilla js**");
		entityManager.persist(c3);
		
		entityManager.flush(); //ne pas oublier le flush sinon le refresh lance une exception (l'object n'est pas trouvé ) 
		
		c2.setName("Angular js ++");
		c3.setName("Angular js +++");
		entityManager.refresh(c2); //en ragrichissant les données de c2, on exclus l'update. Seul c3 sera updated et non c2
		
		
		entityManager.flush();
	}
	
}
