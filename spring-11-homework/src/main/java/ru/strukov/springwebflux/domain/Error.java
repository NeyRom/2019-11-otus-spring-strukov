package ru.strukov.springwebflux.domain;

import lombok.Data;

/**
 * @author Roman Strukov
 */

@Data
public class Error {
    private final int code;
    private final String message;

    public Error(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
