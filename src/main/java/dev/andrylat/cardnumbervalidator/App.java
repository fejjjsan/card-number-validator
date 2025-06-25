package dev.andrylat.cardnumbervalidator;

import dev.andrylat.cardnumbervalidator.core.CardNumber;
import dev.andrylat.cardnumbervalidator.core.CardValidationRunner;
import dev.andrylat.cardnumbervalidator.validation.CardNumberValidator;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        var runner = CardValidationRunner.builder()
                .scanner(new Scanner(System.in))
                .cardNumber(new CardNumber())
                .cardNumberValidator(new CardNumberValidator())
                .build();
        runner.run();
    }
}





