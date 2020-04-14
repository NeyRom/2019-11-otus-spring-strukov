package ru.strukov.testing.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.core.io.Resource;
import ru.strukov.testing.domain.TestQuestion;
import ru.strukov.testing.service.ConsoleService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Roman Strukov on 29.11.2019.
 */

public class TestQuestionDaoImpl implements TestQuestionDao {
    private final String resource;
    private final ConsoleService consoleService;

    public TestQuestionDaoImpl(String resource, ConsoleService consoleService) {
        this.resource = resource;
        this.consoleService = consoleService;
    }

    @Override
    public List<TestQuestion> getQuestions(Resource resource) {
        List<TestQuestion> questions = new ArrayList<>();
        try {
            InputStreamReader reader = new InputStreamReader(resource.getInputStream());
            questions = new CsvToBeanBuilder<TestQuestion>(reader)
                    .withType(TestQuestion.class)
                    .build()
                    .parse();
        } catch (IllegalStateException ise) {
            consoleService.printMessage("Некорректный формат CSV-файла");
            ise.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.shuffle(questions);
        return questions;
    }

    public String getResource() {
        return resource;
    }
}
