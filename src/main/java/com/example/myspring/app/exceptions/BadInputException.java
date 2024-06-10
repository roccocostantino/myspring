package com.example.myspring.app.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BadInputException extends RuntimeException{

    public BadInputException(String message) {
        super(message);
    }
}
