package ru.strukov.springjpa.view;
/* Created by Roman Strukov in 20.04.2020 */

import ru.strukov.springjpa.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreView {
    String printGenre(Genre genre);
    String printGenres(List<Genre> genres);
}
