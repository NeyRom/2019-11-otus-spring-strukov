package ru.strukov.testing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocaleServiceImpl implements LocaleService {
    private IOService ioService;
    private Locale english = Locale.ENGLISH;
    private Locale russian = new Locale("ru", "RU");

    @Autowired
    public LocaleServiceImpl(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public Locale getUserLocale() {
        ioService.printMessage("Выберите язык тестирования: / Please select language:");
        ioService.printMessage("1 - Русский, 2 - English");
        int selectedLanguage = ioService.getInt();
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
