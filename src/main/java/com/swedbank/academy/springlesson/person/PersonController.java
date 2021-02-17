package com.swedbank.academy.springlesson.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("api/persons")
public class PersonController {

    PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Person>> getPersons() {
        List<Person> list = personService.getAll();
        return new ResponseEntity<List<Person>>(list, HttpStatus.OK);
    }

    @GetMapping("{pid}")
    public ResponseEntity<Person> getPersonById(@PathVariable("pid") Long pid) {
        //try {
            Person person = personService.getById(pid);
            return new ResponseEntity<Person>(person, HttpStatus.OK);
       // } catch(PersonNotFoundException ex) {
        //    return ResponseEntity.notFound().build();
       // }
    }

    @DeleteMapping("{pid}")
    public ResponseEntity<Void> deletePerson(@PathVariable("pid") Long pid) {
        try {
            personService.delete(pid);
            return ResponseEntity.ok().build();
        } catch (PersonNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> addPerson(@RequestBody Person person, UriComponentsBuilder builder) {
        boolean success = personService.save(person);
        if (!success) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/person/{id}").buildAndExpand(person.getPid()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
        personService.update(person);
        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }

}
