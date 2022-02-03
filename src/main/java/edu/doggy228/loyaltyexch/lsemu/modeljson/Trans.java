package edu.doggy228.loyaltyexch.lsemu.modeljson;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Trans {
    @Schema(description = "Ідентифікатор транзакції в системі лояльності")
    @JsonProperty(value = "id")
    private String id;
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
    @Schema(description = "Сума транзакції в гривнях.", example = "100.08")
    @JsonProperty(value = "transAmount")
    private String transAmount;
    @Schema(description = "Сума до спалти в гривнях (за мінусом використаних балів).", example = "5.08")
    @JsonProperty(value = "payAmount")
    private String payAmount;
    @Schema(description = "Використано бонусних балів.", example = "0.0805")
    @JsonProperty(value = "bonusAmountIn")
    private String bonusAmountIn;
    @Schema(description = "Нараховано бонусних балів.", example = "0.0814")
    @JsonProperty(value = "bonusAmountOut")
    private String bonusAmountOut;
    @Schema(description = "Код віртуальної валюти", example = "LTR")
    @JsonProperty(value = "vcAlias")
    private String vcAlias;
    @Schema(description = "Назва віртуальної валюти", example = "літри")
    @JsonProperty(value = "vcName")
    private String vcName;
    @Schema(description = "Призначення платежу.", example = "Оплата товару.")
    @JsonProperty(value = "purpose")
    private String purpose;
}
