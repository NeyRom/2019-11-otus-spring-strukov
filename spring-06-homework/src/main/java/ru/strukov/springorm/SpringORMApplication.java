package ru.strukov.springorm;
/* Created by Roman Strukov in 29.03.2020 */

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.jline.PromptProvider;

@SpringBootApplication
public class SpringORMApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringORMApplication.class, args);
    }

    public PromptProvider bookstorePromptProvider() {
        return () -> new AttributedString("bookstore:>", AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE));
    }
}
