package com.example.socialdemo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ErrorFormat {
    private String message;
    private String details;
    private LocalDate date;
}
