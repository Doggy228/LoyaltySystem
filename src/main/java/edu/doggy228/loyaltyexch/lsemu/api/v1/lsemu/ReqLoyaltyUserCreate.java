package edu.doggy228.loyaltyexch.lsemu.api.v1.lsemu;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class ReqLoyaltyUserCreate {
    @Schema(description = "Телефон користувача", example = "380501234567")
    @JsonProperty(value = "tel")
    private String tel;
}
