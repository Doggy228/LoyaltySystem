package edu.doggy228.loyaltyexch.lsemu.api.v1.lsemu;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "Зняття з бонусного рахунку клієнта для зовнішньої розрахункової системи.")
@Getter
public class ReqTransExternalWithdrawal {
    @Schema(description = "Ідентифікатор транзакції в зовнішній системі")
    @JsonProperty(value = "extrnId")
    private String extrnId;
    @Schema(description = "Ідентифікатор картки користувача")
    @JsonProperty(value = "loyaltyUserId")
    private String loyaltyUserId;
    @Schema(description = "Сума внесення/зняття в балах. Додатня - поповнення. Відʼємна - зняття.", example = "100.0817")
    @JsonProperty(value = "bonusAmountChange")
    private String bonusAmountChange;
    @Schema(description = "Сума зміни в зовнішній системі (в її валюті).", example = "157")
    @JsonProperty(value = "systemAmountChange")
    private String systemAmountChange;
    @Schema(description = "Призначення платежу.", example = "Оплата товару в АЗС ОККО.")
    @JsonProperty(value = "purpose")
    private String purpose;
}
