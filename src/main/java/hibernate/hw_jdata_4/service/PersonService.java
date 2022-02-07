package hibernate.hw_jdata_4.service;

import hibernate.hw_jdata_4.person.Person;
import hibernate.hw_jdata_4.repository.PersonsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonsRepository personsRepository;

    public PersonService(PersonsRepository personsRepository) {
        this.personsRepository = personsRepository;
    }

    public List<Person> getPersonsByCity(String city) {
        return personsRepository.getPersonsByCity(city);
    }

    public List<Person> getPersonsByAgeLower(int age) {
        return personsRepository.getPersonsByAgeLower(age);
    }

    public List<Person> getPersonsByNameAndSurname(String name, String surname) {
        return personsRepository.getPersonsByNameAndSurname(name, surname);
    }
}
