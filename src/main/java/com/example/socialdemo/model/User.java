package com.example.socialdemo.model;

import com.example.socialdemo.constrain.BirthDate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Builder
@Setter
public class User {
    private Integer id;
    @Size(min=2, message = "Name should have at least 2 Character long.")
    private String name;
    @NotNull(message = "The date of birth is required.")
    @BirthDate(message = "The birth date must be greater than or equal to 18.")
    @Past(message = "Birth date must be in past.")
    private LocalDate birthDate;
}
