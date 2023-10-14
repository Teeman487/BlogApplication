package com.adebusoye.blogapplication.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {
    private Long id;

    @NotEmpty(message = "firstName should not be empty or null") // 92
    private String firstName;

    @NotEmpty(message = "lastName should not be empty or null")
    private String lastName;

    @NotEmpty(message = "Email should not be empty or null") // 92
    @Email // 92
    private String email;

    @NotEmpty(message = "Password should not be empty") // 92
    private String password;
}
