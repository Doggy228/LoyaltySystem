package edu.doggy228.loyaltyexch.lsemu.modeldb;

import edu.doggy228.loyaltyexch.lsemu.Utils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;

@Getter
@Setter
public class Trans {
    @Id
    private String id;
    private String loyaltySystemId;
    @Indexed(unique = false)
    private String loyaltyUserId;
    @Indexed(unique = false)
    private String loyaltyUserTel;
    private LocalDateTime transDt;
    private String transAmount;
    private String payAmount;
    private String bonusAmountIn;
    private String bonusAmountOut;
    private String purpose;

    public edu.doggy228.loyaltyexch.lsemu.modeljson.Trans toJson(LoyaltySystem loyaltySystem){
        edu.doggy228.loyaltyexch.lsemu.modeljson.Trans objJson = new edu.doggy228.loyaltyexch.lsemu.modeljson.Trans();
        objJson.setId(getId());
        objJson.setLoyaltySystemId(getLoyaltySystemId());
        objJson.setLoyaltySystemName(loyaltySystem.getName());
        objJson.setLoyaltyUserId(getLoyaltyUserId());
        objJson.setLoyaltyUserTel(getLoyaltyUserTel());
        objJson.setTransDt(Utils.getDateTimeStr(transDt));
        objJson.setTransAmount(getTransAmount());
        objJson.setPayAmount(getPayAmount());
        objJson.setBonusAmountIn(getBonusAmountIn());
        objJson.setBonusAmountOut(getBonusAmountOut());
        objJson.setVcAlias(loyaltySystem.getVcAlias());
        objJson.setVcName(loyaltySystem.getVcName());
        objJson.setPurpose(getPurpose());
        return objJson;
    }

}
