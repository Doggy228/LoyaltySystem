package edu.doggy228.loyaltyexch.lsemu.modeldb;

import edu.doggy228.loyaltyexch.lsemu.Utils;
import edu.doggy228.loyaltyexch.lsemu.modeljson.TransExternalType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;

@Getter
@Setter
public class TransExternal {
    @Id
    private String id;
    @Indexed(unique = false)
    private String extrnId;
    private TransExternalType transExternalType;
    private String loyaltySystemId;
    private String loyaltyUserId;
    private String loyaltyUserTel;
    private LocalDateTime transDt;
    private String bonusAmountChange;
    private String systemAmountChange;
    private String purpose;

    public edu.doggy228.loyaltyexch.lsemu.modeljson.TransExternal toJson(LoyaltySystem loyaltySystem){
        edu.doggy228.loyaltyexch.lsemu.modeljson.TransExternal objJson = new edu.doggy228.loyaltyexch.lsemu.modeljson.TransExternal();
        objJson.setId(getId());
        objJson.setExtrnId(getExtrnId());
        objJson.setTransExternalType(getTransExternalType());
        objJson.setLoyaltySystemId(getLoyaltySystemId());
        objJson.setLoyaltySystemName(loyaltySystem.getName());
        objJson.setLoyaltyUserId(getLoyaltyUserId());
        objJson.setLoyaltyUserTel(getLoyaltyUserTel());
        objJson.setTransDt(Utils.getDateTimeStr(transDt));
        objJson.setBonusAmountChange(getBonusAmountChange());
        objJson.setSystemAmountChange(getSystemAmountChange());
        objJson.setVcAlias(loyaltySystem.getVcAlias());
        objJson.setVcName(loyaltySystem.getVcName());
        objJson.setPurpose(getPurpose());
        return objJson;
    }

}
