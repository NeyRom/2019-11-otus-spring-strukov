package ru.strukov.testing.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Roman Strukov on 18.12.2019.
 */

@DisplayName("Класс Student")
public class StudentTest {
    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student("Roman", "Strukov");
    }

    @DisplayName("Корректно создается конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        assertEquals("Roman", student.getFirstName());
        assertEquals("Strukov", student.getLastName());
    }

    @DisplayName("Корректно возвращает имя")
    @Test
    void shouldCorrectGetFirstName() {
        assertEquals("Roman", student.getFirstName());
    }

    @DisplayName("Корректно возвращает фамилию")
    @Test
    void shouldCorrectGetLastName() {
        assertEquals("Strukov", student.getLastName());
    }

}
