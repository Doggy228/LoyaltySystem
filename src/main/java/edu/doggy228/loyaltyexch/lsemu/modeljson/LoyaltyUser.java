package edu.doggy228.loyaltyexch.lsemu.modeljson;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoyaltyUser {
    @Schema(description = "Ідентифікатор картки користувача в системі лояльності")
    @JsonProperty(value = "id")
    private String id;
    @Schema(description = "Телефон користувача")
    @JsonProperty(value = "tel")
    private String tel;
    @Schema(description = "Ідентифікатор системи лояльності")
    @JsonProperty(value = "loyaltySystemId")
    private String loyaltySystemId;
    @Schema(description = "Поточний баланс картки в системі лояльності")
    @JsonProperty(value = "balanceAmount")
    private String balanceAmount;
    @Schema(description = "Назва системи лояльності")
    @JsonProperty(value = "loyaltySystemName")
    private String loyaltySystemName;
    @Schema(description = "Код віртуальної валюти", example = "SLT")
    @JsonProperty(value = "vcAlias")
    private String vcAlias;
    @Schema(description = "Назва віртуальної валюти")
    @JsonProperty(value = "vcName")
    private String vcName;
}
