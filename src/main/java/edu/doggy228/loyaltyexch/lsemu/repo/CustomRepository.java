package edu.doggy228.loyaltyexch.lsemu.repo;

import edu.doggy228.loyaltyexch.lsemu.modeldb.LoyaltyUser;
import edu.doggy228.loyaltyexch.lsemu.modeldb.Trans;

import java.util.List;

public interface CustomRepository {
    public LoyaltyUser loyaltyUserFindByTelAndSystem(String tel, String loyaltySystemId);
    public List<LoyaltyUser> loyaltyUserFindByTel(String tel);
    public List<Trans> transFindByUserLast100(String loyaltyUserId);
    public List<Trans> transFindByTelLast100(String loyaltyUserTel);
}
