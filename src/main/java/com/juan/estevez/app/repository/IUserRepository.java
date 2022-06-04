package com.juan.estevez.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juan.estevez.app.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmail(String email);

}
