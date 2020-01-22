package org.eezer.service.domain.repository;

import org.eezer.service.domain.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserModel, String> {

    /**
     * Get a user by it's username.
     *
     * @param username the username to find
     * @return the user object, if found
     */
    UserModel getByUsername(String username);

}