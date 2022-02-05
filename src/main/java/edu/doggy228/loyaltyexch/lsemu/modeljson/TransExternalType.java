package edu.doggy228.loyaltyexch.lsemu.modeljson;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TransExternalType {
    @JsonProperty("replenishment")
    REPLENISHMENT,
    @JsonProperty("withdrawal")
    WITHDRAWAL,
}
