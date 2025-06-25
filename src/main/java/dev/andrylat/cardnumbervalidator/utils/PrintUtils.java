package dev.andrylat.cardnumbervalidator.utils;

import dev.andrylat.cardnumbervalidator.core.CardNetwork;

import java.util.List;

public class PrintUtils {

    public static void printErrors(List<String> errors) {
        System.out.println("Errors:");
        errors.forEach(err -> System.out.println("  -> " + err));
    }

    public static void greetingMessage() {
        System.out.println("Hello! Enter card number for validation");
    }

    public static void successfulValidation(CardNetwork cardNetWork) {
        var string = String.format("Card is valid. Payment network is %s.", cardNetWork);
        System.out.println(string);
    }

    public static void failedValidationMessage() {
        System.out.println("Card number is not valid!");
    }

}
