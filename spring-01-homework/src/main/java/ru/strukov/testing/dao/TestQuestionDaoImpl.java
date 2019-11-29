package ru.strukov.testing.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.core.io.Resource;
import ru.strukov.testing.domain.TestQuestion;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Roman Strukov on 29.11.2019.
 */

public class TestQuestionDaoImpl implements TestQuestionDao {
    private final String resource;

    public TestQuestionDaoImpl(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }

    public void prepareResource(Resource resource) {
        try {
            File file = resource.getFile();
            System.out.println(file.getAbsolutePath());
            CSVReader reader = new CSVReader(new FileReader(file));
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                System.out.println(nextLine[0] + "/" + nextLine[1]);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TestQuestion getQuestion() {
        return null;
    }
}
