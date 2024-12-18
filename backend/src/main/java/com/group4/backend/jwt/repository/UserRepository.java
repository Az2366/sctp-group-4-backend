package com.group4.backend.jwt.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.group4.backend.jwt.model.User;

import java.util.List;

// To ensure that spring data rest does not provide end points that exposes our user.
@RepositoryRestResource(exported = false)
public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findByUsername(String username);
}
