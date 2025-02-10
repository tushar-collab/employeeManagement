package com.ps.assignment.employeeManagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ps.assignment.employeeManagement.dto.GenericResult;
import com.ps.assignment.employeeManagement.dto.UserDto;
import com.ps.assignment.employeeManagement.model.User;
import com.ps.assignment.employeeManagement.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
public class UserController {

    private static final Logger LOG = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/loadUsers")
    public GenericResult loadUsers() {
        LOG.info("Loading users from external API");
        GenericResult result = new GenericResult();
        Boolean status = false;
        org.json.JSONObject apiData = userService.getDataFromExternalAPI();
        if (apiData != null && apiData.has("users")) {
            JSONArray usersArray = (JSONArray) apiData.get("users");
            LOG.info("Total users found :: " + usersArray.length());
            status = userService.loadUsersFromExternalAPI(usersArray);
            result.setStatus("SUCCESS");
            result.setMessage("Users loaded successfully");
            result.setSuccess(status);
        } else {
            LOG.error("Failed to load users from external API");
            result.setStatus("FAILURE");
            result.setMessage("Failed to load users from external API");
            result.setSuccess(status);
        }
        return result;
    }

    @GetMapping("/findAllUsers")
    public GenericResult findAllUsers() {
        LOG.info("Finding all users");
        GenericResult result = new GenericResult();
        List<UserDto> users = userService.findAllUser();
        if (!users.isEmpty()) {
            result.setStatus("SUCCESS");
            result.setMessage("Users found successfully");
            result.setSuccess(true);
            result.setData(users);
        } else {
            result.setStatus("FAILURE");
            result.setMessage("Failed to find users");
            result.setSuccess(false);
        }
        return result;
    }

    @PostMapping("/findByFirstName")
    public GenericResult findByFirstName(@RequestBody JSONObject requestData) {
        LOG.info("Finding users by first name :: " + requestData.get("name"));
        GenericResult result = new GenericResult();
        List<UserDto> users = null;
        try {
            users = userService.findByFirstName(requestData.get("name").toString());
            if (!users.isEmpty()) {
                result.setStatus("SUCCESS");
                result.setMessage("Users found successfully");
                result.setSuccess(true);
                result.setData(users);
            } else {
                result.setStatus("FAILURE");
                result.setMessage("Failed to find users");
                result.setSuccess(true);
            }
        } catch (Exception e) {
            LOG.error("Error finding users by first name :: " + e.getMessage());
            result.setStatus("FAILURE");
            result.setMessage("Error finding users by first name :: " + e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }

    @PostMapping("/findByLastName")
    public GenericResult findByLastName(@RequestBody JSONObject requestData) {
        LOG.info("Finding users by Last name :: " + requestData.get("name"));
        GenericResult result = new GenericResult();
        List<UserDto> users = null;
        try {
            users = userService.findByLastName(requestData.get("name").toString());
            if (!users.isEmpty()) {
                result.setStatus("SUCCESS");
                result.setMessage("Users found successfully");
                result.setSuccess(true);
                result.setData(users);
            } else {
                result.setStatus("FAILURE");
                result.setMessage("Failed to find users");
                result.setSuccess(true);
            }
        } catch (Exception e) {
            LOG.error("Error finding users by first name :: " + e.getMessage());
            result.setStatus("FAILURE");
            result.setMessage("Error finding users by first name :: " + e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }

    @PostMapping("/findBySsn")
    public GenericResult findBySsn(@RequestBody JSONObject requestData) {
        LOG.info("Finding users by Ssn :: " + requestData.get("name"));
        GenericResult result = new GenericResult();
        List<UserDto> users = null;
        try {
            users = userService.findBySsn(requestData.get("ssn").toString());
            if (!users.isEmpty()) {
                result.setStatus("SUCCESS");
                result.setMessage("Users found successfully");
                result.setSuccess(true);
                result.setData(users);
            } else {
                result.setStatus("FAILURE");
                result.setMessage("Failed to find users");
                result.setSuccess(true);
            }
        } catch (Exception e) {
            LOG.error("Error finding users by first name :: " + e.getMessage());
            result.setStatus("FAILURE");
            result.setMessage("Error finding users by first name :: " + e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }

    @PostMapping("/doFreeSearch")
    public GenericResult doFreeSearch(@RequestBody JSONObject requestData) {
        LOG.info("Doing free search :: " + requestData.get("searchStr"));
        GenericResult result = new GenericResult();
        List<UserDto> users = new ArrayList<>();
        try {
            users.addAll(userService.findByFirstName(requestData.get("name").toString()));
            users.addAll(userService.findByLastName(requestData.get("name").toString()));
            users.addAll(userService.findBySsn(requestData.get("name").toString()));
            if (!users.isEmpty()) {
                result.setStatus("SUCCESS");
                result.setMessage("Users found successfully");
                result.setSuccess(true);
                result.setData(users);
            } else {
                result.setStatus("FAILURE");
                result.setMessage("Failed to find users");
                result.setSuccess(true);
            }
        } catch (Exception e) {
            result.setStatus("FAILURE");
            result.setMessage("Failed to find users");
            result.setSuccess(false);
            LOG.error("Error doing free search :: " + e.getMessage());
        }
        return result;
    }

    @PostMapping("/findUserById")
    public GenericResult findUserById(@RequestBody JSONObject requestData) {
        LOG.info("Finding user by id :: " + requestData.get("id"));
        GenericResult result = new GenericResult();
        UserDto user = new UserDto();
        try {
            Integer id = Integer.parseInt(requestData.get("id").toString());
            user = userService.findUserById(id);
            if (user != null) {
                result.setStatus("SUCCESS");
                result.setMessage("User found successfully");
                result.setSuccess(true);
                result.setData(user);
            } else {
                result.setStatus("FAILED");
                result.setSuccess(true);
                result.setMessage("User not found");
            }
        } catch (Exception e) {
            result.setStatus("FAILURE");
            result.setMessage("Failed to find user");
            result.setSuccess(false);
            LOG.error("Error finding user by id :: " + e.getMessage());
        }
        return result;
    }
}
