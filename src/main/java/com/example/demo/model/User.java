package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "id_generator")
    @SequenceGenerator(
            name = "id_generator",
            sequenceName = "id_sequence",
            initialValue = 1
    )
    private Long userId;

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @NotBlank
    @Size(min = 2, max = 100)
    private String surname;

    @Size(max = 250)
    private String address;

    @Size(max = 100)
    @Email
    private String email;
}
