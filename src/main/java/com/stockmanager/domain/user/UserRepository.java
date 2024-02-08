package com.stockmanager.domain.user;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
    Optional<User> findByUserName(String userName);

    boolean existsByUserName(String userName);
}
