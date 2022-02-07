package hibernate.hw_jdata_4.repository;

import hibernate.hw_jdata_4.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
@Repository
public class PersonsRepository {

    @Autowired
    private PersonCrudRepository personCrudRepository;

    public PersonsRepository() {}

    public List<Person> getPersonsByCity(String city) {
        return personCrudRepository.findAllByCityNameIgnoreCase(city);
    }

    public List<Person> getPersonsByAgeLower(int age) {
        return personCrudRepository.findByIdAgeLessThan(age, Sort.by("id.age"));
    }

    public List<Person> getPersonsByNameAndSurname(String name, String surname) {
        return personCrudRepository.findByIdNameAndIdSurnameIgnoreCase(name, surname);
    }
}
