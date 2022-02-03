package edu.doggy228.loyaltyexch.lsemu.api.v1.lsemu;


import com.fasterxml.jackson.annotation.JsonProperty;
import edu.doggy228.loyaltyexch.lsemu.modeljson.LoyaltySystem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RspListLoyaltySystem {
    @Schema(description = "Список систем лояльності.")
    @JsonProperty(value = "listLoyaltySystem")
    private LoyaltySystem[] listLoyaltySystem;
}
