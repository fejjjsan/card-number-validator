package dev.andrylat.cardnumbervalidator.validation;

import dev.andrylat.cardnumbervalidator.core.CardNetwork;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CardValidationResult {
    private final List<String> errors;
    private CardNetwork cardNetwork;

    public boolean errorsIsEmpty() {
        return errors.isEmpty();
    }
}
