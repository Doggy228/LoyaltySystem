package edu.doggy228.loyaltyexch.lsemu.api.v1.lsemu;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RspTransPayCheck {
    @Schema(description = "Ідентифікатор системи лояльності")
    @JsonProperty(value = "loyaltySystemId")
    private String loyaltySystemId;
    @Schema(description = "Назва системи лояльності")
    @JsonProperty(value = "loyaltySystemName")
    private String loyaltySystemName;
    @Schema(description = "Ідентифікатор картки користувача в системі лояльності. Якщо пусте - не є клієнтом")
    @JsonProperty(value = "loyaltyUserid")
    private String loyaltyUserid;
    @Schema(description = "Телефон користувача. Якщо пусте - не є клієнтом")
    @JsonProperty(value = "loyaltyUserTel")
    private String loyaltyUserTel;
    @Schema(description = "Поточний баланс картки в системі лояльності. Якщо не є клієнтом то нуль.")
    @JsonProperty(value = "loyaltyUserBalanceAmount")
    private String loyaltyUserBalanceAmount;
    @Schema(description = "Код віртуальної валюти", example = "SLT")
    @JsonProperty(value = "vcAlias")
    private String vcAlias;
    @Schema(description = "Назва віртуальної валюти")
    @JsonProperty(value = "vcName")
    private String vcName;
    @Schema(description = "Курс відповідності віртуальної валюти до гривні. Наприклад, якщо балл це копійка, то курс 0.01", example = "0.01")
    @JsonProperty(value = "vcRate")
    private String vcRate;
    @Schema(description = "Кількість цифр дробної частини", example = "2")
    @JsonProperty(value = "vcScale")
    private int vcScale;
    @Schema(description = "Сума транзакції в гривнях. Береться з запиту.", example = "100.08")
    @JsonProperty(value = "transAmount")
    private String transAmount;
    @Schema(description = "Максимально допустима кількість балів для використання.", example = "1.0014")
    @JsonProperty(value = "bonusAmountInMax")
    private String bonusAmountInMax;
    @Schema(description = "Буде нараховано бонусних балів.", example = "0.0814")
    @JsonProperty(value = "bonusAmountOut")
    private String bonusAmountOut;

}
