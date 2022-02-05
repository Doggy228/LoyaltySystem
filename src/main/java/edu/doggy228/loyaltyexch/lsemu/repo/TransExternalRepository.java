package edu.doggy228.loyaltyexch.lsemu.repo;

import edu.doggy228.loyaltyexch.lsemu.modeldb.TransExternal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransExternalRepository extends MongoRepository<TransExternal, String> {
}
