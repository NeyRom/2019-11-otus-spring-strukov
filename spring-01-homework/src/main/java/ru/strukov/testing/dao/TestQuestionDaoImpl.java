package ru.strukov.testing.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.core.io.Resource;
import ru.strukov.testing.domain.TestQuestion;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roman Strukov on 29.11.2019.
 */

public class TestQuestionDaoImpl implements TestQuestionDao {
    private final String resource;

    public TestQuestionDaoImpl(String resource) {
        this.resource = resource;
    }

    @Override
    public List<TestQuestion> getQuestions(Resource resource) {
        List<TestQuestion> questions = new ArrayList<>();
        try {
            File file = resource.getFile();
            questions = new CsvToBeanBuilder<TestQuestion>(new FileReader(file))
                    .withType(TestQuestion.class)
                    .build()
                    .parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questions;
    }

    public String getResource() {
        return resource;
    }

}
