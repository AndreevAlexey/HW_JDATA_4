package hibernate.hw_jdata_4.repository;

import hibernate.hw_jdata_4.person.Person;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonCrudRepository extends JpaRepository<Person, Long> {

    // полчить список личностей по городу
    @Query(value = "select p from Person p where upper(p.city.name) = upper(:city)")
    List<Person> findAllByCityNameIgnoreCase(@Param("city") String name);
    // полчить список личностей младше возраста
    @Query(value = "select p from Person p where p.id.age < :age")
    List<Person> findByIdAgeLessThan(@Param("age") int age, Sort sort);
    // полчить список личностей по имени и фамилии
    @Query(value = "select p from Person p where p.id.name = :name and p.id.surname = :surname")
    List<Person> findByIdNameAndIdSurnameIgnoreCase(@Param("name") String name, @Param("surname") String surname);
}
