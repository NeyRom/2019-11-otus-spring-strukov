package ru.strukov.springjpa.view;
/* Created by Roman Strukov in 20.04.2020 */

import org.springframework.stereotype.Component;
import ru.strukov.springjpa.domain.Genre;
import ru.strukov.springjpa.service.PrintService;

import java.util.List;

@Component
public class GenreViewImpl implements GenreView {
    private final PrintService printService;

    public GenreViewImpl(PrintService printService) {
        this.printService = printService;
    }

    @Override
    public String printGenre(Genre genre) {
        return "Жанр #" + printService.printGenre(genre);
    }

    @Override
    public String printGenres(List<Genre> genres) {
        StringBuilder text = new StringBuilder("Список жанров:").append(System.lineSeparator());
        genres.forEach(genre -> text.append(printService.printGenre(genre))
                                    .append(System.lineSeparator()));
        return text.toString();
    }

}
