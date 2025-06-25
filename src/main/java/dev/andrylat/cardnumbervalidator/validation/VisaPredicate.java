package dev.andrylat.cardnumbervalidator.validation;

import java.util.function.Predicate;

public class VisaPredicate implements Predicate<String> {
    private final static String FOUR_PREFIX = "4";

    @Override
    public boolean test(String string) {
        return string.startsWith(FOUR_PREFIX);
    }
}
