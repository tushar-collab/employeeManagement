package com.ps.assignment.employeeManagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ps.assignment.employeeManagement.dto.GenericResult;
import com.ps.assignment.employeeManagement.dto.UserDto;
import com.ps.assignment.employeeManagement.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
@Tag(name = "User CRUD operations", description = "User CRUD operations")
public class UserController {

    private static final Logger LOG = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor trimmer = new StringTrimmerEditor(true);
        JSONObject obj = (JSONObject) binder.getTarget();
        if (!obj.isEmpty()) {
            Set<String> keys = (Set<String>) obj.keySet();
        }
        binder.registerCustomEditor(String.class, trimmer);
    }

    @GetMapping("/findAllUsers")
    @Operation(summary = "Load Users", description = "Load users to grid from DB", tags = { "User CRUD operations" })
    @ApiResponse(responseCode = "201", description = "Users fetched successfully")
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
    @Operation(summary = "Load Users based on first name", description = "Load users to grid based on user's first name from DB", tags = { "User CRUD operations" })
    @ApiResponse(responseCode = "201", description = "Users fetched successfully")
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
    @Operation(summary = "Load Users based on last name", description = "Load users to grid based on user's last name from DB", tags = { "User CRUD operations" })
    @ApiResponse(responseCode = "201", description = "Users fetched successfully")
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
    @Operation(summary = "Load Users based on Ssn", description = "Load users to grid based on user's Ssn from DB", tags = { "User CRUD operations" })
    @ApiResponse(responseCode = "201", description = "Users fetched successfully")
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
    @Operation(summary = "Do free Search", description = "Load users to grid based on user's first name/ last name/ SSn from DB", tags = { "User CRUD operations" })
    @ApiResponse(responseCode = "201", description = "Users fetched successfully")
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
    @Operation(summary = "Load User based on id", description = "Load users to grid based on user's id from DB", tags = { "User CRUD operations" })
    @ApiResponse(responseCode = "201", description = "Users fetched successfully")
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
