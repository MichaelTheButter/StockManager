package com.stockmanager.core.user;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
    Optional<UserRole> findByName(String name);
}
