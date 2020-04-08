package ru.strukov.jdbc;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.jline.PromptProvider;


@SpringBootApplication
public class JdbcApplication {
    public static void main(String[] args) {
        SpringApplication.run(JdbcApplication.class, args);
    }

    @Bean
    public PromptProvider bookstorePromptProvider() {
        return () -> new AttributedString("bookstore:>", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
    }
}
