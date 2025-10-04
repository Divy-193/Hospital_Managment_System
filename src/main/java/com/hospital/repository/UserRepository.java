package com.hospital.repository;

import com.hospital.entity.User;
import org.springframework.data.repository.Repository;

import java.util.Optional;
@org.springframework.stereotype.Repository
public interface UserRepository extends Repository<User, Integer> {

    Optional<User> findByUsername(String username);
}