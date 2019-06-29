package com.restTracker.restTracker.persistence;

import com.restTracker.restTracker.model.jwt.JwtRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<JwtRequest,String> {

    JwtRequest findByUsername(String username);

}
