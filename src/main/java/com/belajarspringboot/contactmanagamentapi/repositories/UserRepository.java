package com.belajarspringboot.contactmanagamentapi.repositories;

import com.belajarspringboot.contactmanagamentapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
