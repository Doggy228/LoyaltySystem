package edu.doggy228.loyaltyexch.lsemu.api.v1.lsemu;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AmountValueType {
    @JsonProperty("none")
    NONE,
    @JsonProperty("full")
    FULL,
    @JsonProperty("manual")
    MANUAL,
}
