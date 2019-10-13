package com.example.demo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
