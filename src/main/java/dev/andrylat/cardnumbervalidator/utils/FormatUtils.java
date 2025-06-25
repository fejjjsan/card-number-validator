package dev.andrylat.cardnumbervalidator.utils;

public class FormatUtils {
    public static String deleteSpaceCharacters(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input must not be null");
        }
        return input.replaceAll("\\s+", "");
    }
}
