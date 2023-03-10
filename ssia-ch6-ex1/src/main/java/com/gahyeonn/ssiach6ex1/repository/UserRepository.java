package com.gahyeonn.ssiach6ex1.repository;

import com.gahyeonn.ssiach6ex1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByUsername(String u);
}
