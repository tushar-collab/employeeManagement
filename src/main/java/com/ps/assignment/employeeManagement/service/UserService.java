package com.ps.assignment.employeeManagement.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ps.assignment.employeeManagement.model.User;

public interface UserService {

    public Boolean loadUsersFromExternalAPI(JSONArray usersArray);

    public List<User> findByFirstName(String firstName);

    public List<User> findByLastName(String lastName);
    
     public JSONObject getDataFromExternalAPI();
}
