package edu.doggy228.loyaltyexch.lsemu.api.v1.lsemu;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "Конфігурація нової системи лояльності.")
@Getter
public class ReqLoyaltySystemCreate {
    @Schema(description = "Ідентифікатор системи лояльності", required = true)
    @JsonProperty(value = "id")
    private String id;
    @Schema(description = "Назва системи лояльності", required = true)
    @JsonProperty(value = "name")
    private String name;
    @Schema(description = "Код віртуальної валюти", required = true, example = "SLT")
    @JsonProperty(value = "vcAlias")
    private String vcAlias;
    @Schema(description = "Назва віртуальної валюти", required = true)
    @JsonProperty(value = "vcName")
    private String vcName;
    @Schema(description = "Курс відповідності до гривні. Наприклад, якщо балл це копійка, то курс 0.01", required = true, example = "0.01")
    @JsonProperty(value = "vcRate")
    private String vcRate;
    @Schema(description = "Частка суми платежу, яка зараховується у вигляді бонусу клієнта. Наприклад 2% це 0.02", required = true, example = "0.02")
    @JsonProperty(value = "vcKoef")
    private String vcKoef;
    @Schema(description = "Кількість цифр дробної частини", required = true, example = "2")
    @JsonProperty(value = "vcScale")
    private int vcScale;
}
