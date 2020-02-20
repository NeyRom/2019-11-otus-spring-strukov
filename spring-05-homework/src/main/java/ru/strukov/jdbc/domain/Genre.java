package ru.strukov.jdbc.domain;
/* Created by Roman Strukov in 13.02.2020 */

import lombok.Getter;
import lombok.Setter;

public class Genre {
    @Getter @Setter
    private long id;
    @Getter @Setter
    private String title;

    public Genre(String title) {
        this.title = title.toLowerCase();
    }

    public Genre(long id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("Genre#%d - %s", id, title);
    }
}
