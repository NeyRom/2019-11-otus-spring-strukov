package ru.strukov.springwebflux.exception;

/**
 * @author Roman Strukov
 */

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
