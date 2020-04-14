package ru.strukov.testing.service;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by Roman Strukov on 08.12.2019.
 */

public class ConsoleServiceImpl implements ConsoleService {
    private final Scanner input;
    private final PrintStream output;

    public ConsoleServiceImpl() {
        this.output = System.out;
        this.input = new Scanner(System.in);
    }

    @Override
    public String getStringWithMessage(String message) {
        output.println(message);
        return input.nextLine();
    }

    @Override
    public int getInt() {
        int givenAnswer;
        while (true) {
            try {
                givenAnswer = Integer.parseInt(input.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ожидается ввод числа");
            }
        }
        return givenAnswer;
    }

    @Override
    public void printMessage(String message) {
        output.println(message);
    }
}
