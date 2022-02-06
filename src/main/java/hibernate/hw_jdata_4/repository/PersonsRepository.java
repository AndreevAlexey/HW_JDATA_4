package hibernate.hw_jdata_4.repository;

import hibernate.hw_jdata_4.person.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Transactional
@Repository
public class PersonsRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public PersonsRepository() {}

    public List<Person> getPersonsByCity(String city) {
        Query query = entityManager.createQuery("select p from Person p " +
                                                   "where p.city_of_living is not null " +
                                                   "and p.city_of_living = (select c from City c where upper(c.name) = upper(:city))"
                                                , Person.class);
        query.setParameter("city", city);
        List<Person> persons = query.getResultList();
        return persons;
    }
}
