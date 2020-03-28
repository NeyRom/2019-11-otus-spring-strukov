package ru.strukov.jdbc.domain;
/* Created by Roman Strukov in 13.02.2020 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre)) return false;
        Genre genre = (Genre) o;
        return id == genre.getId() &&
                Objects.equals(title, genre.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
