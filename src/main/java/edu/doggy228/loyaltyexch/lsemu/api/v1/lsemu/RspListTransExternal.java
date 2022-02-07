package edu.doggy228.loyaltyexch.lsemu.api.v1.lsemu;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.doggy228.loyaltyexch.lsemu.modeljson.TransExternal;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RspListTransExternal {
    @Schema(description = "Список операцій зовнішніх зарахування/зняття.")
    @JsonProperty(value = "listTransExternal")
    private TransExternal[] listTransExternal;
}
