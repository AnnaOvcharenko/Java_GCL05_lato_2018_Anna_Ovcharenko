package com.ovcharenko.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PictureNotFoundException extends RuntimeException {
    public PictureNotFoundException(String s) {
        super(s);
    }
}
