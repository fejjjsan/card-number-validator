package dev.andrylat.cardnumbervalidator.core;

import dev.andrylat.cardnumbervalidator.utils.FormatUtils;
import dev.andrylat.cardnumbervalidator.utils.PrintUtils;
import dev.andrylat.cardnumbervalidator.validation.CardNumberValidator;
import lombok.Builder;

import java.util.Scanner;

@Builder
public class CardValidationRunner {

    private final Scanner scanner;
    private final CardNumber cardNumber;
    private final CardNumberValidator cardNumberValidator;

    public void run() {
        PrintUtils.greetingMessage();

        var input = scanner.nextLine();
        var normalizedInput = FormatUtils.deleteSpaceCharacters(input);
        cardNumber.setCardNumber(normalizedInput);

        var validationResult = cardNumberValidator.validate(cardNumber);

        if (validationResult.isPresent()) {
            var result = validationResult.get();
            if (result.errorsIsEmpty()) {
                PrintUtils.successfulValidation(result.getCardNetwork());
            } else {
                PrintUtils.failedValidationMessage();
                PrintUtils.printErrors(result.getErrors());
            }
        }
    }
}