package hibernate.hw_jdata_4.controller;

import hibernate.hw_jdata_4.person.Person;
import hibernate.hw_jdata_4.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons/by-city")
    @Secured("ROLE_READ")
    public List<Person> getPersonsByCity(@RequestParam("city") String city) {
        return personService.getPersonsByCity(city);
    }


    @GetMapping("/persons/by-age-lower")
    @RolesAllowed("ROLE_WRITE")
    public List<Person> getPersonsByAgeLower(@RequestParam("age") int age) {
        return personService.getPersonsByAgeLower(age);
    }

    @GetMapping("/persons/by-name-surname")
    @PreAuthorize("hasRole('ROLE_DELETE')")
    public List<Person> getPersonsByNameAndSurname(@RequestParam("name") String name, @RequestParam("surname") String surname) {
        return personService.getPersonsByNameAndSurname(name, surname);
    }

    @GetMapping("/persons/userinfo")
    @PreAuthorize("#username == authentication.principal.username")
    public String getUserInfo(@RequestParam("username") String username) {
        return SecurityContextHolder.getContext().getAuthentication().toString();
    }

    @ExceptionHandler
    ResponseEntity<String> handlerAccessDeniedException(AccessDeniedException exp) {
        return new ResponseEntity<>(exp.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler
    ResponseEntity<String> handlerRuntimeException(Exception exp) {
        exp.printStackTrace();
        return new ResponseEntity<>("Ошибка обработки запроса", HttpStatus.SERVICE_UNAVAILABLE);
    }



}
