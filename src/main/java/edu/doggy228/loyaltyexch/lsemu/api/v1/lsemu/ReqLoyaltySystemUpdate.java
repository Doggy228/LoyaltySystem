package edu.doggy228.loyaltyexch.lsemu.api.v1.lsemu;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "Нові параметри конфігурації системи лояльності.")
@Getter
public class ReqLoyaltySystemUpdate {
    @Schema(description = "Назва системи лояльності")
    @JsonProperty(value = "name")
    private String name;
    @Schema(description = "Код віртуальної валюти")
    @JsonProperty(value = "vcAlias")
    private String vcAlias;
    @Schema(description = "Назва віртуальної валюти")
    @JsonProperty(value = "vcName")
    private String vcName;
    @Schema(description = "Курс відповідності до гривні. Наприклад, якщо балл це копійка, то курс 0.01", example = "0.01")
    @JsonProperty(value = "vcRate")
    private String vcRate;
    @Schema(description = "Частка суми платежу, яка зараховується у вигляді бонусу клієнта. Наприклад 2% це 0.02", example = "0.02")
    @JsonProperty(value = "vcKoef")
    private String vcKoef;
    @Schema(description = "Кількість цифр дробної частини. <0 - не змінювати.", example = "-1")
    @JsonProperty(value = "vcScale")
    private int vcScale;
}
