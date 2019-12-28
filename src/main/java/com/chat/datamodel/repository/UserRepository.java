package com.chat.datamodel.repository;

import com.chat.datamodel.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ganeshallampalli
 */

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUserId(String userId);
}
