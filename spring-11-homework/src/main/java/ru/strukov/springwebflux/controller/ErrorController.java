package ru.strukov.springwebflux.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.strukov.springwebflux.domain.Error;
import ru.strukov.springwebflux.exception.NotFoundException;

/**
 * @author Roman Strukov
 */

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Error> handleNotFoundException(NotFoundException exception) {
        final Error error = new Error(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
