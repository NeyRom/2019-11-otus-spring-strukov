package ru.strukov.testing.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.strukov.testing.dao.StudentDao;
import ru.strukov.testing.domain.Student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Проверка сервиса StudentService")
class StudentServiceTest {
    private StudentDao studentDao;
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentDao = mock(StudentDao.class);
        studentService = new StudentServiceImpl(studentDao);
    }

    @DisplayName("Корректно устанавливается ФИ студента")
    @Test
    void shouldSetName() {
        when(studentDao.createStudent("Roman", "Strukov")).thenReturn(new Student("Roman", "Strukov"));
        assertEquals(new Student("Roman", "Strukov"), studentService.setName("Roman", "Strukov"));
    }

    @DisplayName("Корректно возвращается ФИ студента")
    @Test
    void shouldGetFullName() {
        String student = studentService.getFullName(new Student("Roman", "Strukov"));
        assertThat(student).isNotEmpty().contains("Strukov").contains(" ");
    }
}