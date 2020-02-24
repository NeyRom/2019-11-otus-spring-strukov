package ru.strukov.jdbc.domain;
/* Created by Roman Strukov in 13.02.2020 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class Genre {
    @Getter @Setter
    private long id;
    @Getter @Setter
    private String title;

    public Genre(String title) {
        this.title = title.toLowerCase();
    }

    @Override
    public String toString() {
        return String.format("%d - %s", id, title);
    }
}
