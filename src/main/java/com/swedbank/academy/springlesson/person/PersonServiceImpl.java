package com.swedbank.academy.springlesson.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService{

    private PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @Override
    public Person getById(long pid) {
        return personRepository.findById(pid).orElseThrow(() -> new PersonNotFoundException(pid));
    }

    @Override
    public void delete(long pid) {
        Person person = personRepository.findById(pid).orElseThrow(() -> new PersonNotFoundException(pid));
        personRepository.deleteById(pid);
    }

    @Override
    public boolean save(Person person) {
        Optional<Person> p = personRepository.findById(person.getPid());
        if (p.isPresent())
            return false;
        else {
            personRepository.save(person);
            return true;
        }
    }

    @Override
    public boolean update(Person person) throws PersonNotFoundException {
        boolean doneUpdate = false;
        long pid = person.getPid();
        Optional<Person> p = personRepository.findById(pid);
        if (p.isPresent()) {
            personRepository.save(person);
            doneUpdate = true;
        }
     else {
            throw new PersonNotFoundException(pid);
        }
        return doneUpdate;
    }

}
