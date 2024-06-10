package com.example.myspring.app.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NullElementException extends RuntimeException{

    public NullElementException(String message) {
        super(message);
    }
}
