package edu.doggy228.loyaltyexch.lsemu.modeljson;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransExternal {
    @Schema(description = "Ідентифікатор транзакції в системі лояльності")
    @JsonProperty(value = "id")
    private String id;
    @Schema(description = "Ідентифікатор транзакції в зовнішній системі")
    @JsonProperty(value = "extrnId")
    private String extrnId;
    @Schema(description = "Тип транзакції. replenishment - поповнення рахунку, withdrawal - зняття коштів")
    @JsonProperty(value = "transExternalType")
    private TransExternalType transExternalType;
    @Schema(description = "Ідентифікатор системи лояльності", example = "sokar")
    @JsonProperty(value = "loyaltySystemId")
    private String loyaltySystemId;
    @Schema(description = "Назва системи лояльності", example = "АЗС Сокар")
    @JsonProperty(value = "loyaltySystemName")
    private String loyaltySystemName;
    @Schema(description = "Ідентифікатор картки користувача")
    @JsonProperty(value = "loyaltyUserId")
    private String loyaltyUserId;
    @Schema(description = "Телефон користувача", example = "380501234567")
    @JsonProperty(value = "loyaltyUserTel")
    private String loyaltyUserTel;
    @Schema(description = "Дата та час транзакції.", pattern = "YYYY-MM-DDThh:mm:ss", example = "2021-12-08T13:43:01")
    @JsonProperty(value = "transDt")
    private String transDt;
    @Schema(description = "Сума внесення/зняття в балах. Додатня - поповнення. Відʼємна - зняття.", example = "100.0817")
    @JsonProperty(value = "bonusAmountChange")
    private String bonusAmountChange;
    @Schema(description = "Сума зміни в зовнішній системі (в її валюті).", example = "157")
    @JsonProperty(value = "systemAmountChange")
    private String systemAmountChange;
    @Schema(description = "Код віртуальної валюти", example = "LTR")
    @JsonProperty(value = "vcAlias")
    private String vcAlias;
    @Schema(description = "Назва віртуальної валюти", example = "літри")
    @JsonProperty(value = "vcName")
    private String vcName;
    @Schema(description = "Призначення платежу.", example = "Оплата товару в АЗС ОККО.")
    @JsonProperty(value = "purpose")
    private String purpose;
}

