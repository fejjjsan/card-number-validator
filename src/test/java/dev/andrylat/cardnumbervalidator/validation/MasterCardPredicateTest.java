package dev.andrylat.cardnumbervalidator.validation;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MasterCardPredicateTest {
    private final MasterCardPredicate predicate = new MasterCardPredicate();

    @Test
    public void test_Valid_MasterCard_Prefix_Begins_With_Five() {
        var prefix1 = "51";
        var prefix2 = "53";
        var prefix3 = "55";

        assertThat(predicate.test(prefix1)).isTrue();
        assertThat(predicate.test(prefix2)).isTrue();
        assertThat(predicate.test(prefix3)).isTrue();
    }

    @Test
    public void test_Valid_MasterCard_Prefix_Begins_With_Two() {
        var prefix1 = "2221";
        var prefix2 = "2500";
        var prefix3 = "2720";

        assertThat(predicate.test(prefix1)).isTrue();
        assertThat(predicate.test(prefix2)).isTrue();
        assertThat(predicate.test(prefix3)).isTrue();
    }

    @Test
    public void test_Invalid_MasterCard_Prefix() {
        var prefix1 = "50";
        var prefix2 = "56";
        var prefix3 = "2220";
        var prefix4 = "2722";

        assertThat(predicate.test(prefix1)).isFalse();
        assertThat(predicate.test(prefix2)).isFalse();
        assertThat(predicate.test(prefix3)).isFalse();
        assertThat(predicate.test(prefix4)).isFalse();
    }
}
