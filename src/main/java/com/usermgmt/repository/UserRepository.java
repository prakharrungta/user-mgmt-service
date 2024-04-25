package com.usermgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usermgmt.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
