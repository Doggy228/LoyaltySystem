package edu.doggy228.loyaltyexch.lsemu.modeldb;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;


@Getter
@Setter
public class LoyaltyUser {
    @Id
    private String id;
    @Indexed(unique = false)
    private String tel;
    @Indexed(unique = false)
    private String loyaltySystemId;
    private String balanceAmount;

    public edu.doggy228.loyaltyexch.lsemu.modeljson.LoyaltyUser toJson(LoyaltySystem loyaltySystem){
        edu.doggy228.loyaltyexch.lsemu.modeljson.LoyaltyUser objJson = new edu.doggy228.loyaltyexch.lsemu.modeljson.LoyaltyUser();
        objJson.setId(getId());
        objJson.setTel(getTel());
        objJson.setLoyaltySystemId(getLoyaltySystemId());
        objJson.setBalanceAmount(getBalanceAmount());
        objJson.setLoyaltySystemName(loyaltySystem.getName());
        objJson.setVcAlias(loyaltySystem.getVcAlias());
        objJson.setVcName(loyaltySystem.getVcName());
        return objJson;
    }
}
