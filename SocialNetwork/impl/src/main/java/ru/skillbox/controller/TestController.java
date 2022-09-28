package ru.skillbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.repository.PersonRepository;


@RestController
@RequestMapping("/api")
public class TestController {

    private final PersonRepository personDAO;

    @Autowired
    public TestController(PersonRepository personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping("/sayHello")
    public String sayHallo() {
        personDAO.getAllUsers().forEach(person -> {
            System.out.println(person.getName() + " " + person.getLastname());
        });
        return "Hello World!";
    }
}

