package ru.strukov.testing.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.strukov.testing.domain.Student;
import ru.strukov.testing.service.LocaleService;
import ru.strukov.testing.service.StudentService;
import ru.strukov.testing.service.TestQuestionService;

import java.util.Locale;

@ShellComponent
public class TestingCommands {
    private final LocaleService localeService;
    private final MessageSource messageSource;
    private final StudentService studentService;
    private final TestQuestionService testQuestionService;
    private Locale locale;
    private Student student;

    @Autowired
    public TestingCommands(LocaleService localeService, MessageSource messageSource,
                           StudentService studentService, TestQuestionService testQuestionService) {
        this.localeService = localeService;
        this.messageSource = messageSource;
        this.studentService = studentService;
        this.testQuestionService = testQuestionService;
    }

    @ShellMethod(value = "Select user locale, 1 for Russian, 2 for English", key = {"select-locale", "sl"})
    public String setUserLocale(@ShellOption int select) {
        this.locale = localeService.setUserLocale(select);
        return messageSource.getMessage("Locale.set", null, locale);
    }

    @ShellMethod(value = "Set student First Name (--fn) and Last Name (--ln)", key = {"set-student", "ss"})
    @ShellMethodAvailability("checkSetStudentAvailability")
    public String setStudent(
            @ShellOption String fn,
            @ShellOption String ln
    ) {
        student = studentService.setName(fn, ln);
        return messageSource.getMessage("Student.setting",
                new Object[] {studentService.getFullName(student)},
                locale);
    }

    @ShellMethod(value = "Conduct testing", key = {"conduct-test", "ct"})
    @ShellMethodAvailability("checkConductTestingAvailability")
    public void conductTest() {
        testQuestionService.conductTesting(student, locale);
    }

    public Availability checkSetStudentAvailability() {
        return locale != null
                ? Availability.available()
                : Availability.unavailable("you must select locale first");
    }

    public Availability checkConductTestingAvailability() {
        return student != null
                ? Availability.available()
                : Availability.unavailable("you must set student first");
    }
}
