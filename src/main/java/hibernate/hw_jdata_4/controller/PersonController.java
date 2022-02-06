package hibernate.hw_jdata_4.controller;

import hibernate.hw_jdata_4.person.Person;
import hibernate.hw_jdata_4.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons/by-city")
    public List<Person> getPersonsByCity(@RequestParam("city") String city) {
        return personService.getPersonsByCity(city);
    }

    @ExceptionHandler
    ResponseEntity<String> handlerRuntimeException(RuntimeException exp) {
        exp.printStackTrace();
        return new ResponseEntity<>("Ошибка обработки запроса", HttpStatus.SERVICE_UNAVAILABLE);
    }

}
