package dev.andrylat.cardnumbervalidator.validation;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VisaPredicateTest {

    @Test
    public void test_Valid_MasterCard_Prefix_Begins_With_Four() {
        var predicate = new VisaPredicate();
        var prefix = "4";

        assertThat(predicate.test(prefix)).isTrue();
    }

    @Test
    public void test_Invalid_MasterCard_Prefix() {
        var predicate = new VisaPredicate();
        var prefix = "3";

        assertThat(predicate.test(prefix)).isFalse();
    }
}
