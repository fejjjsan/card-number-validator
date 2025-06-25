package dev.andrylat.cardnumbervalidator.validation;

import dev.andrylat.cardnumbervalidator.core.CardNumber;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardNumberValidatorTest {

    public static final String ERROR_LENGTH_INVALID = "Length should be 16 symbols.";
    public static final String ERROR_NOT_A_DIGIT = "Number should contain only digits.";
    public static final String ERROR_UNKNOWN_NETWORK = "Payment network is unknown.";
    public static final String ERROR_NOT_RECOGNIZED = "Card number is not recognized.";

    private final static CardNumberValidator validator = new CardNumberValidator();

    @Test
    public void validation_Is_Failed_CardNumber_Is_Null() {
        var result = validator.validate(null);

        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    public void cardNumber_Has_Invalid_Length() {
        var cardNumber = new CardNumber("100010001000100");
        var result = validator.validate(cardNumber);

        assertThat(result.isPresent()).isTrue();

        var errors = result.get().getErrors();

        assertThat(errors.size()).isEqualTo(1);
        assertThat(errors).contains(CardNumberValidator.ERROR_LENGTH_INVALID);
    }

    @Test
    public void cardNumber_Has_Invalid_Character() {
        var cardNumber = new CardNumber("100000000000000E");
        var result = validator.validate(cardNumber);

        assertThat(result.isPresent()).isTrue();

        var errors = result.get().getErrors();

        assertThat(errors.size()).isEqualTo(1);
        assertThat(errors).contains(ERROR_NOT_A_DIGIT);
    }

    @Test
    public void cardNumber_Has_Unknown_Card_Network() {
        var cardNumber = new CardNumber("5000550000000029"); // <--- Maestro network is not supported
        var result = validator.validate(cardNumber);

        assertThat(result.isPresent()).isTrue();

        var errors = result.get().getErrors();

        assertThat(errors.size()).isEqualTo(1);
        assertThat(errors).contains(ERROR_UNKNOWN_NETWORK);
    }

    @Test
    public void cardNumber_Is_Not_Recognized_Invalid_Check_Sum() {
        var cardNumber = new CardNumber("4000056655665555"); // <--- Maestro network is not supported
        var result = validator.validate(cardNumber);

        assertThat(result.isPresent()).isTrue();

        var errors = result.get().getErrors();

        assertThat(errors.size()).isEqualTo(1);
        assertThat(errors).contains(ERROR_NOT_RECOGNIZED);
    }

    @Test
    public void cardNumber_Has_Invalid_Length_And_Invalid_Character() {
        var cardNumber = new CardNumber("A00005665566555"); // <--- Maestro network is not supported
        var result = validator.validate(cardNumber);

        assertThat(result.isPresent()).isTrue();

        var errors = result.get().getErrors();

        assertThat(errors.size()).isEqualTo(2);
        assertThat(errors).contains(ERROR_LENGTH_INVALID);
        assertThat(errors).contains(ERROR_NOT_A_DIGIT);
    }

    @Test
    public void cardNumber_Has_Unknown_Network_And_Is_Not_Recognized_Invalid_Check_Sum() {
        var cardNumber = new CardNumber("1000056655665555");
        var result = validator.validate(cardNumber);

        assertThat(result.isPresent()).isTrue();

        var errors = result.get().getErrors();

        assertThat(errors.size()).isEqualTo(2);
        assertThat(errors).contains(ERROR_UNKNOWN_NETWORK);
        assertThat(errors).contains(ERROR_NOT_RECOGNIZED);
    }

    @Test
    public void cardNumber_Is_Valid_Visa_Card() {
        var cardNumber = new CardNumber("4000056655665556");
        var result = validator.validate(cardNumber);

        assertThat(result.isPresent()).isTrue();

        var errors = result.get().getErrors();

        assertThat(errors.size()).isEqualTo(0);
    }

    @Test
    public void cardNumber_Is_Valid_Master_Card() {
        var cardNumber = new CardNumber("5454545454545454");
        var result = validator.validate(cardNumber);

        assertThat(result.isPresent()).isTrue();

        var errors = result.get().getErrors();

        assertThat(errors.size()).isEqualTo(0);
    }
}
