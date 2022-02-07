package hibernate.hw_jdata_4;

import hibernate.hw_jdata_4.city.City;
import hibernate.hw_jdata_4.person.Identification;
import hibernate.hw_jdata_4.person.Person;
import hibernate.hw_jdata_4.repository.CityCrudRepository;
import hibernate.hw_jdata_4.repository.PersonCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
public class CommandLineApp implements CommandLineRunner {

    @Autowired
    private CityCrudRepository cityCrudRepository;

    @Autowired
    private PersonCrudRepository personCrudRepository;

    /*
    @PersistenceContext
    private EntityManager entityManager;
    */
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        List<City> cities = Stream.of("MOSCOW", "TULA", "PERM", "KURSK")
                .map(n->City.builder()
                        .name(n)
                        .build())
                .collect(Collectors.toList());

        cityCrudRepository.saveAll(cities);

        List<String> names = List.of("Alexey", "Ivan", "Petr", "Dmitry");
        List<String> surnames = List.of("Alekseev", "Ivanov", "Petrov", "Dmitriev");

        List<Person> persons = new ArrayList<>();
        Random random = new Random();
        IntStream.range(10, 50)
                .forEach(i->{
                    Person person = Person.builder()
                            .id(Identification.builder()
                                    .name(names.get(random.nextInt(names.size())))
                                    .surname(surnames.get(random.nextInt(surnames.size())))
                                    .age(i)
                                    .build())
                            .phone_number("7778999555")
                            .city(cities.get(random.nextInt(cities.size())))
                            .build();

                    persons.add(person);
                });
        personCrudRepository.saveAll(persons);
    }
}
