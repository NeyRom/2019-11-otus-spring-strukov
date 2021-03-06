package ru.strukov.springmongo.domain;

/* Created by Roman Strukov in 11.05.2020 */

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Comment {

    private String content;

    public Comment(String content) {
        this.content = content;
    }
}
