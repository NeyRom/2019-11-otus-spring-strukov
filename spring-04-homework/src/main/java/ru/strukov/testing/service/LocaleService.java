package ru.strukov.testing.service;

import java.util.Locale;

public interface LocaleService {
    Locale setUserLocale(int selectedLanguage);
    String getPathComponent(Locale locale);
}
