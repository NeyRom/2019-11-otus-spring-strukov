package ru.strukov.testing.service;

import java.util.Locale;

public interface LocaleService {
    Locale getUserLocale();
    String getPathComponent(Locale locale);
}
