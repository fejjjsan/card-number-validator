package dev.andrylat.cardnumbervalidator.validation;

import dev.andrylat.cardnumbervalidator.core.CardNetwork;
import dev.andrylat.cardnumbervalidator.core.CardNetworkRegistry;
import dev.andrylat.cardnumbervalidator.core.CardNumber;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

public class CardNumberValidator {

    public static final String ERROR_LENGTH_INVALID = "Length should be 16 symbols.";
    public static final String ERROR_NOT_A_DIGIT = "Number should contain only digits.";
    public static final String ERROR_UNKNOWN_NETWORK = "Payment network is unknown.";
    public static final String ERROR_NOT_RECOGNIZED = "Card number is not recognized.";
    public static final int VALID_CARD_NUMBER_LENGTH = 16;


    public Optional<CardValidationResult> validate(CardNumber cardNumber) {
        if (cardNumber == null) {
            return Optional.empty();
        }

        List<String> errors = new ArrayList<>();

        var number = cardNumber.getCardNumber();

        if (!cardNumberHasValidLength(number)) {
            errors.add(ERROR_LENGTH_INVALID);
        }

        if (!numberContainsOnlyDigits(number)) {
            errors.add(ERROR_NOT_A_DIGIT);
        }

        if (errors.isEmpty()) {
            var prefix = cardNumber.extractPrefix();
            var cardNetwork = CardNetworkRegistry.detectNetworkFrom(prefix);
            if (cardNetwork.equals(CardNetwork.UNKNOWN)) {
                errors.add(ERROR_UNKNOWN_NETWORK);
            }
            if (!isValidCardNumber(number)) {
                errors.add(ERROR_NOT_RECOGNIZED);
            }
            return Optional.of(CardValidationResult.builder()
                    .errors(errors)
                    .cardNetwork(cardNetwork)
                    .build());
        }

        return Optional.of(CardValidationResult.builder()
                .errors(errors)
                .build());
    }


    private static boolean isValidCardNumber(String cardNumber) {
        if (cardNumber == null) {
            return false;
        }

        char[] chars = cardNumber.toCharArray();
        var sum = 0;
        for (int i = chars.length - 2; i >= 0; i--) {
            var item = Character.getNumericValue(chars[i]);
            if (i % 2 == 0) {
                item *= 2;
                sum = item < 10 ? sum + item : sum + (item - 9); // 14 -> 1 + 4 = 5 same as 14 - 9 = 5
                continue;
            }
            sum += item;
        }

        var checkDigit = Character.getNumericValue(chars[chars.length - 1]);
        sum += checkDigit;

        return sum % 10 == 0;
    }

    private static boolean numberContainsOnlyDigits(String string) {
        for (var ch : string.toCharArray()) {
            if (ch < '0' || ch > '9') {
                return false;
            }
        }
        return true;
    }

    private static boolean cardNumberHasValidLength(String cardNumber) {
        return cardNumber.length() == VALID_CARD_NUMBER_LENGTH;
    }

}
