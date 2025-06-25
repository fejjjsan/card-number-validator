package dev.andrylat.cardnumbervalidator.validation;

import java.util.function.Predicate;

public class MasterCardPredicate implements Predicate<String> {

    private final static String TWO_PREFIX = "2";
    private final static int TWO_PREFIX_LENGTH = 4;
    private final static int TWO_PREFIX_LOWER_BOUND = 2221;
    private final static int TWO_PREFIX_UPPER_BOUND = 2720;

    private final static String FIVE_PREFIX = "5";
    private final static int FIVE_PREFIX_LENGTH = 2;
    private final static int FIVE_PREFIX_LOWER_BOUND = 51;
    private final static int FIVE_PREFIX_UPPER_BOUND = 55;


    @Override
    public boolean test(String string) {
        if (string.startsWith(FIVE_PREFIX)) {
            return isWithinRange(string, FIVE_PREFIX_LENGTH, FIVE_PREFIX_LOWER_BOUND, FIVE_PREFIX_UPPER_BOUND);
        } else if (string.startsWith(TWO_PREFIX)) {
            return isWithinRange(string, TWO_PREFIX_LENGTH, TWO_PREFIX_LOWER_BOUND, TWO_PREFIX_UPPER_BOUND);
        }
        return false;
    }

    private boolean isWithinRange(String string, int prefixLength, int low, int max) {
        var prefix = string.substring(0, prefixLength);
        var intPrefix = Integer.parseInt(prefix);
        return intPrefix >= low && intPrefix <= max;
    }

}
