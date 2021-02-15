package com.swedbank.academy.springlesson.person;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@Table(name="person")
public class Person {

    @Id
    @NotNull
    private long pid;

    @NotNull
    @NotBlank
    @Column(name = "first_name")
    private String name;

    @Column(name = "middle_name")
    private String middleName;

    @NotNull
    @NotBlank
    @Column(name = "last_name")
    private String surname;

    private String email;

    private String phone;
}
