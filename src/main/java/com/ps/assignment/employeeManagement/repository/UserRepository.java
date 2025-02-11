package com.ps.assignment.employeeManagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ps.assignment.employeeManagement.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    public Optional<User> findByEmail(String email);

    @Query("Select u.id, u.firstName, u.lastName, u.maidenName, u.email, u.phone,u.company.department, u.age, u.ssn from User u")
    public List<Object[]> fetchUsers();

    @Query("Select u.id, u.firstName, u.lastName, u.maidenName, u.email, u.phone,u.company.department, u.age, u.university from User u where u.firstName LIKE %:fName%")
    public Optional<List<Object[]>> findUsersByFirstName(@Param("fName") String firstName);

    @Query("Select u.id, u.firstName, u.lastName, u.maidenName, u.email, u.phone,u.company.department, u.age, u.university from User u where u.lastName LIKE %:lName%")
    public Optional<List<Object[]>> findUsersByLastName(@Param("lName") String lastName);

    @Query("Select u.id, u.firstName, u.lastName, u.maidenName, u.email, u.phone,u.company.department, u.age, u.university from User u where u.ssn LIKE %:ssn%")
    public Optional<List<Object[]>> findUsersBySsn(@Param("ssn") String ssn);

    public Optional<User> findById(Integer id);
}
