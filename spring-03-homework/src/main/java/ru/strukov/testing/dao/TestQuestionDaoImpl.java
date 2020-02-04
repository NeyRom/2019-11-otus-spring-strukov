package ru.strukov.testing.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import ru.strukov.testing.domain.TestQuestion;
import ru.strukov.testing.service.IOService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Roman Strukov on 29.11.2019.
 */

@Service
public class TestQuestionDaoImpl implements TestQuestionDao {
    private final String resourceName;
    private final String resourceExtension;
    private final IOService ioService;
    private final ResourceLoader resourceLoader;

    @Autowired
    public TestQuestionDaoImpl(@Value("${resource.name}") String resourcePath,
                               @Value("${resource.extension}") String resourceExtension,
                               IOService ioService,
                               ResourceLoader resourceLoader) {
        this.resourceName = resourcePath;
        this.resourceExtension = resourceExtension;
        this.ioService = ioService;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public List<TestQuestion> getQuestions(String pathComponent) {
        String resourcePath = "classpath:" + resourceName + pathComponent + resourceExtension;
        Resource resource = resourceLoader.getResource(resourcePath);
        List<TestQuestion> questions = new ArrayList<>();
        try {
            InputStreamReader reader = new InputStreamReader(resource.getInputStream());
            questions = new CsvToBeanBuilder<TestQuestion>(reader)
                    .withType(TestQuestion.class)
                    .build()
                    .parse();
        } catch (IllegalStateException ise) {
            ioService.printMessage("Некорректный формат CSV-файла");
            ise.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.shuffle(questions);
        return questions;
    }

}
