package com.swedbank.academy.springlesson.person;

import java.util.List;

public interface PersonService {

    List<Person> getAll();

    Person getById(long pid);

    void delete(long pid);

}

