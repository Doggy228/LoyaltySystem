package edu.doggy228.loyaltyexch.lsemu.repo;

import edu.doggy228.loyaltyexch.lsemu.modeldb.LoyaltyUser;
import edu.doggy228.loyaltyexch.lsemu.modeldb.Trans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomRepositoryImpl implements CustomRepository{
    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public LoyaltyUser loyaltyUserFindByTelAndSystem(String tel, String loyaltySystemId) {
        final Query query = new Query()
                .addCriteria(new Criteria().andOperator(
                        Criteria.where("tel").is(tel),
                        Criteria.where("loyaltySystemId").is(loyaltySystemId)
                ));
        List<LoyaltyUser> list = mongoTemplate.find(query, LoyaltyUser.class);
        if(list==null || list.isEmpty()) return null;
        return list.get(0);
    }

    @Override
    public List<LoyaltyUser> loyaltyUserFindByTel(String tel) {
        final Query query = new Query()
                .addCriteria(Criteria.where("tel").is(tel));
        return mongoTemplate.find(query, LoyaltyUser.class);
    }

    @Override
    public List<Trans> transFindByUserLast100(String loyaltyUserId) {
        final Pageable pageableRequest = PageRequest.of(0, 100);
        final Query query = new Query()
                .addCriteria(Criteria.where("loyaltyUserId").is(loyaltyUserId))
                .with(Sort.by(Sort.Direction.DESC, "transDt"))
                .with(pageableRequest);
        return mongoTemplate.find(query, Trans.class);
    }

    @Override
    public List<Trans> transFindByTelLast100(String loyaltyUserTel) {
        final Pageable pageableRequest = PageRequest.of(0, 100);
        final Query query = new Query()
                .addCriteria(Criteria.where("loyaltyUserTel").is(loyaltyUserTel))
                .with(Sort.by(Sort.Direction.DESC, "transDt"))
                .with(pageableRequest);
        return mongoTemplate.find(query, Trans.class);
    }
}
