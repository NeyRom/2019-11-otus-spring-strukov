package ru.strukov.springorm.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.strukov.springorm.model.Comment;

import static org.assertj.core.api.Assertions.assertThat;

/* Created by Roman Strukov in 05.04.2020 */

@DataJpaTest
@DisplayName("Класс CommentRepositoryImpl")
@Import(CommentRepositoryImpl.class)
class CommentRepositoryImplTest {

    @Autowired
    private CommentRepositoryImpl commentRepository;

    private Comment sameComment;
    private Comment anotherComment;

    @BeforeEach
    void setUp() {
        sameComment = new Comment("Пушкин - наше все", 1);
        anotherComment = new Comment("Ацтой", 4);
    }

    @Test
    @DisplayName("возвращает список комментариев к указанной книге")
    void shouldFindAllByBookId() {
        assertThat(commentRepository.findAllByBookId(1))
                .hasSize(2)
                .contains(sameComment)
                .doesNotContain(anotherComment);
    }

    @Test
    @DisplayName("добавляет комментарий")
    void shouldInsertComment() {
        commentRepository.insert(anotherComment);
        assertThat(commentRepository.findAllByBookId(4))
                .hasSize(1)
                .contains(anotherComment);
    }
}