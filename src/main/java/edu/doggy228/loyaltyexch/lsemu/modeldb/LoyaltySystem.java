package edu.doggy228.loyaltyexch.lsemu.modeldb;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class LoyaltySystem {
    @Id
    private String id;
    private String name;
    private String vcAlias;
    private String vcName;
    private String vcRate;
    private String vcKoef;
    private int vcScale;

    public edu.doggy228.loyaltyexch.lsemu.modeljson.LoyaltySystem toJson(){
        edu.doggy228.loyaltyexch.lsemu.modeljson.LoyaltySystem objJson = new edu.doggy228.loyaltyexch.lsemu.modeljson.LoyaltySystem();
        objJson.setId(getId());
        objJson.setName(getName());
        objJson.setVcAlias(getVcAlias());
        objJson.setVcName(getVcName());
        objJson.setVcRate(getVcRate());
        objJson.setVcKoef(getVcKoef());
        objJson.setVcScale(getVcScale());
        return objJson;
    }
}
