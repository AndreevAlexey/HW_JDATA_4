package hibernate.hw_jdata_4.repository;

import hibernate.hw_jdata_4.person.Person;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonCrudRepository extends JpaRepository<Person, Long> {

    // полчить список личностей по городу
    List<Person> findAllByCityNameIgnoreCase(String name);
    // полчить список личностей младше возраста
    List<Person> findByIdAgeLessThan(int age, Sort sort);
    // полчить список личностей по имени и фамилии
    List<Person> findByIdNameAndIdSurnameIgnoreCase(String name, String surname);
}
