package dev.andrylat.cardnumbervalidator.core;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardNetworkRegistryTest {
    @Test
    public void detects_MasterCard_Network_From_ValidPrefix() {
        var validMasterCardPrefix1 = "51";
        var validMasterCardPrefix2 = "55";
        var validMasterCardPrefix3 = "2221";
        var validMasterCardPrefix4 = "2720";
        assertThat(CardNetworkRegistry.detectNetworkFrom(validMasterCardPrefix1)).isEqualTo(CardNetwork.MASTER_CARD);
        assertThat(CardNetworkRegistry.detectNetworkFrom(validMasterCardPrefix2)).isEqualTo(CardNetwork.MASTER_CARD);
        assertThat(CardNetworkRegistry.detectNetworkFrom(validMasterCardPrefix3)).isEqualTo(CardNetwork.MASTER_CARD);
        assertThat(CardNetworkRegistry.detectNetworkFrom(validMasterCardPrefix4)).isEqualTo(CardNetwork.MASTER_CARD);
    }

    @Test
    public void detects_Visa_Network_From_ValidPrefix() {
        var validVisaPrefix = "4";
        assertThat(CardNetworkRegistry.detectNetworkFrom(validVisaPrefix)).isEqualTo(CardNetwork.VISA);
    }

    @Test
    public void fails_To_Detect_Network_From_UnknownPrefix() {
        var unknownCardPrefix = "0";
        assertThat(CardNetworkRegistry.detectNetworkFrom(unknownCardPrefix)).isEqualTo(CardNetwork.UNKNOWN);
    }

    @Test
    public void fails_To_Detect_Network_From_BlankPrefix() {
        var unknownCardPrefix = "";
        assertThat(CardNetworkRegistry.detectNetworkFrom(unknownCardPrefix)).isEqualTo(CardNetwork.UNKNOWN);
    }

    @Test
    public void fails_To_Detect_Network_From_NullPrefix() {
        assertThat(CardNetworkRegistry.detectNetworkFrom(null)).isEqualTo(CardNetwork.UNKNOWN);
    }

}
