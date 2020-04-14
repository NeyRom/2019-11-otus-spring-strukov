package ru.strukov.testing.service;

import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LocaleServiceImpl implements LocaleService {
    private Locale english = Locale.ENGLISH;
    private Locale russian = new Locale("ru", "RU");

    @Override
    public Locale setUserLocale(int selectedLanguage) {
        if (selectedLanguage == 1) {
            return russian;
        } else {
            return english;
        }
    }

    @Override
    public String getPathComponent(Locale locale) {
        return locale == russian ? "ru" : "en";
    }

}
