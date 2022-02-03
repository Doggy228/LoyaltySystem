package edu.doggy228.loyaltyexch.lsemu.api.v1.lsemu;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "Платіжна операція з POS терміналу.")
@Getter
public class ReqTransPay {
    @Schema(description = "Сума операції в гривнях.", required = true, example = "102.08")
    @JsonProperty(value = "transAmount")
    private String transAmount;
    @Schema(description = "Як використовуються бонуси: none - не використовуються, full - максимально можливо, manual - сума вказана в полі bonusAmountIn.", required = true, example = "manual")
    @JsonProperty(value = "bonusType")
    private AmountValueType bonusType;
    @Schema(description = "Сума використовуємих бонусних балів якщо вказано manual.", example = "0.005")
    @JsonProperty(value = "bonusAmountIn")
    private String bonusAmountIn;
    @Schema(description = "Призначення платежу.", example = "Оплата товару.")
    @JsonProperty(value = "purpose")
    private String purpose;
}
