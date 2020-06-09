package ru.strukov.springwebflux.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Roman Strukov
 */

@Data
@NoArgsConstructor
public class Comment {
    private String content;
    private LocalDateTime time;

    public Comment(String content) {
        this.content = content;
        time = LocalDateTime.now();
    }
}
