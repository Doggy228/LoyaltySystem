package edu.doggy228.loyaltyexch.lsemu.repo;

import edu.doggy228.loyaltyexch.lsemu.modeldb.LoyaltyUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoyaltyUserRepository extends MongoRepository<LoyaltyUser, String > {
}
