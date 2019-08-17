package com.ernanefernandes.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ernanefernandes.workshopmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
