package ru.strukov.springmongo.domain;
/* Created by Roman Strukov in 11.05.2020 */

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Genre {

    private String title;

    public Genre(String title) {
        this.title = title;
    }
}
