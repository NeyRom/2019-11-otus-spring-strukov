package ru.strukov.springjpa;/* Created by Roman Strukov in 14.04.2020 */

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.shell.jline.PromptProvider;

public class SpringJpaApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringJpaApplication.class, args);
    }

    public PromptProvider bookstorePromptProvider() {
        return () -> new AttributedString("bookstore:>",
                AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE));
    }
}
