package hibernate.hw_jdata_4;

import hibernate.hw_jdata_4.city.City;
import hibernate.hw_jdata_4.person.Identification;
import hibernate.hw_jdata_4.person.Person;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
public class CommandLineApp implements CommandLineRunner {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private Flyway flyway;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // добавляем записи в таблицу city
        List<City> cities = Stream.of("MOSCOW", "TULA", "PERM", "KURSK")
                .map(n->City.builder()
                        .name(n)
                        .build())
                .collect(Collectors.toList());

        for(City city : cities) {
            // добавление
            entityManager.persist(city);
        }
        // данные для генерации
        List<String> names = List.of("Alexey", "Ivan", "Petr", "Dmitry");
        List<String> surnames = List.of("Alekseev", "Ivanov", "Petrov", "Dmitriev");
        // генерация и добавление записей в таблицу persons
        Random random = new Random();
        IntStream.range(10, 50)
                .forEach(i->{
                    Person person = Person.builder()
                            .identification(Identification.builder()
                                    .name(names.get(random.nextInt(names.size())))
                                    .surname(surnames.get(random.nextInt(surnames.size())))
                                    .age(i)
                                    .build())
                            .phone_number("7778999555")
                            .city_of_living(cities.get(random.nextInt(cities.size())))
                            .build();
                    // добавление
                    entityManager.persist(person);
                });
        // запуск миграции
        flyway.migrate();
    }
}
