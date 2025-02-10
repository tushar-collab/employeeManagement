package com.ps.assignment.employeeManagement.dao;

import java.util.List;
import java.util.Optional;

import com.ps.assignment.employeeManagement.dto.UserDto;

public interface UserDao {
    
    public Optional<List<UserDto>> getUserList();

}
