package dev.andrylat.cardnumbervalidator.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CardNumber {
    private final static int PREFIX_MAX_LENGTH = 4;
    private String cardNumber;

    public CardNumber(String number) {
        this.cardNumber = number;
    }

    public String extractPrefix() {
        return cardNumber.substring(0, PREFIX_MAX_LENGTH);
    }

    @Override
    public String toString() {
        return String.format("Card number: %s", cardNumber);
    }
}
