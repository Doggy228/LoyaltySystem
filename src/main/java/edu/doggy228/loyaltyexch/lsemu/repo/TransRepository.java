package edu.doggy228.loyaltyexch.lsemu.repo;

import edu.doggy228.loyaltyexch.lsemu.modeldb.Trans;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransRepository extends MongoRepository<Trans, String> {
}
