package ru.strukov.springorm.service;
/* Created by Roman Strukov in 08.04.2020 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.strukov.springorm.model.Genre;
import ru.strukov.springorm.repository.GenreRepository;

import java.util.Optional;

@Component
public class GenreServiceOrmImpl implements GenreService {

    private final GenreRepository repository;
    private final PrintService printService;

    @Autowired
    public GenreServiceOrmImpl(GenreRepository repository, PrintService printService) {
        this.repository = repository;
        this.printService = printService;
    }

    @Override
    public String printAllGenres() {
        StringBuilder genres = new StringBuilder("Список жанров:").append(System.lineSeparator());
        repository.findAll().forEach(genre -> genres.append(printService.printGenre(genre))
                                                    .append(System.lineSeparator()));
        return genres.toString();
    }

    @Override
    public String printGenreById(long id) {
        Optional<Genre> genre = repository.findById(id);
        return genre.map(value -> "Жанр #" + printService.printGenre(value))
                    .orElse("Жанр не найден");
    }

}
