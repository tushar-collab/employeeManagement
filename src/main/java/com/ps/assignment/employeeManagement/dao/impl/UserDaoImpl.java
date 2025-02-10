package com.ps.assignment.employeeManagement.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ps.assignment.employeeManagement.dao.UserDao;
import com.ps.assignment.employeeManagement.dto.UserDto;

import jakarta.persistence.EntityManager;

public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Optional<List<UserDto>> getUserList() {
        Optional<List<UserDto>> users = Optional.empty();
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

}
