package edu.doggy228.loyaltyexch.lsemu.repo;

import edu.doggy228.loyaltyexch.lsemu.modeldb.LoyaltySystem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoyaltySystemRepository extends MongoRepository<LoyaltySystem, String> {
}
