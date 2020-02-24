package ru.strukov.jdbc.service;
/* Created by Roman Strukov in 22.02.2020 */

import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.Scanner;

@Service
public class ConsoleServiceImpl implements IoService {
    private final Scanner input;
    private final PrintStream output;

    public ConsoleServiceImpl() {
        this.output = System.out;
        this.input = new Scanner(System.in);
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
