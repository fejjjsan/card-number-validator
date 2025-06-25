package dev.andrylat.cardnumbervalidator.core;

import dev.andrylat.cardnumbervalidator.validation.MasterCardPredicate;
import dev.andrylat.cardnumbervalidator.validation.VisaPredicate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.function.Predicate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CardNetworkRegistry {
    private final static Map<CardNetwork, Predicate<String>> RULES = Map.of(CardNetwork.VISA, new VisaPredicate(), CardNetwork.MASTER_CARD, new MasterCardPredicate());

    public static CardNetwork detectNetworkFrom(String prefix) {
        if (prefix == null || prefix.isBlank()) {
            return CardNetwork.UNKNOWN;
        }

        for (CardNetwork brand : RULES.keySet()) {
            if (RULES.get(brand).test(prefix)) {
                return brand;
            }
        }

        return CardNetwork.UNKNOWN;
    }
}
