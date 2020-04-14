package ru.strukov.testing.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.strukov.testing.domain.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Roman Strukov on 01.12.2019.
 */

@DisplayName("Класс Student")
public class StudentTest {

    @DisplayName("корректно создается конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        Student student = new Student("Roman", "Strukov");

        assertEquals("Roman", student.getFirstName());
        assertEquals("Strukov", student.getLastName());
    }
}
