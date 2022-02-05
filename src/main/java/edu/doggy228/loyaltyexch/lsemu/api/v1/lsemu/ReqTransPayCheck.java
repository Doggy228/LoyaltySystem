package edu.doggy228.loyaltyexch.lsemu.api.v1.lsemu;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "Плануєма платіжна операція з POS терміналу.")
@Getter
public class ReqTransPayCheck {
    @Schema(description = "Сума операції в гривнях.", required = true, example = "102.08")
    @JsonProperty(value = "transAmount")
    private String transAmount;
}
