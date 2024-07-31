package ir.samanehgroup.task.controllers;

import ir.samanehgroup.task.models.Person;
import ir.samanehgroup.task.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PersonController {

    PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/Person")
    public ResponseEntity<?> create(@RequestBody Person person) {
        Person done = personService.create(person);
        Map<String, String> response = new HashMap<>();
        response.put("Message", "Person is created successfully!");
        response.put("Person", done.toString());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/Person/{nationalID}")
    public ResponseEntity<?> find(@PathVariable int nationalID) {
        Person done = personService.findByNationalId(nationalID);
        Map<String, String> response = new HashMap<>();
        response.put("Message", "Person is found successfully!");
        response.put("Person", done.toString());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/Person")
    public ResponseEntity<?> update(@RequestBody Person person) {
        Person done = personService.update(person);
        Map<String, String> response = new HashMap<>();
        response.put("Message", "Person is updated successfully!");
        response.put("Person", done.toString());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/Person/{nationalID}")
    public ResponseEntity<?> delete(@PathVariable int nationalID) {
        personService.delete(nationalID);
        Map<String, String> response = new HashMap<>();
        response.put("Message", "Person is deleted successfully!");
        return ResponseEntity.ok(response);
    }

}
