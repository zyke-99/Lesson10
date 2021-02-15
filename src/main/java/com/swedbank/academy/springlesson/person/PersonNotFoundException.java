package com.swedbank.academy.springlesson.person;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(long pid) {
        super("Can't find person with id " + pid);
    }
}
