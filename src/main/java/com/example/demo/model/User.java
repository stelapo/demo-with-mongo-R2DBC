package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;

@Data
@Document(collection = "users")
public class User {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private Long userId;

    @NotBlank(message = "Name mustn't be blank")
    @Size(min = 2, max = 100, message = "Name length should be between 2 and 100")
    private String name;

    @NotBlank(message = "Surname mustn't be blank")
    @Size(min = 2, max = 100, message = "Surname length should be between 2 and 100")
    private String surname;

    @Size(max = 250, message = "Address length should be max 250")
    private String address;

    @Size(max = 100, message = "Email length should be max 250")
    @Email(message = "Email should be valid")
    private String email;
}
