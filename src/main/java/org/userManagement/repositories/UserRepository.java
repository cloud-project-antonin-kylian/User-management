package org.userManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.userManagement.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findById(int userId);
}
