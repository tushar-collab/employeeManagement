package com.ps.assignment.employeeManagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ps.assignment.employeeManagement.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.firstName LIKE %?1%")
    public Optional<List<User>> findByFirstName(String firstName);

    @Query("SELECT u FROM User u WHERE u.lastName LIKE %?1%")
    public Optional<List<User>> findByLastName(String lastName);
}
