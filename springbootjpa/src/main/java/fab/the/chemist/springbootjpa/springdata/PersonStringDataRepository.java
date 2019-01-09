package fab.the.chemist.springbootjpa.springdata;

import org.springframework.data.jpa.repository.JpaRepository;

import fab.the.chemist.springbootjpa.entity.Person;

public interface PersonStringDataRepository extends JpaRepository<Person, Integer> {

}
