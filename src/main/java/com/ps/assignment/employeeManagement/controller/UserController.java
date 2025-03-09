package com.ps.assignment.employeeManagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    @Operation(summary = "Load Users from external API", description = "Load users from external API", tags = {
            "User CRUD operations" })
    @ApiResponse(responseCode = "200", description = "Users loaded successfully")
    @PostMapping("/doInitialSetup")
    public GenericResult doInitialSetup() {
        LOG.info("Finding all users");
        GenericResult result = new GenericResult();
        Boolean status = userService.doInitialSetup();
        if (status) {
            result.setStatus("SUCCESS");
            result.setMessage("Users loaded successfully");
            result.setSuccess(true);
        } else {
            result.setStatus("FAILURE");
            result.setMessage("Failed to load users");
            result.setSuccess(false);
        }
        return result;
    }

    @PostMapping("/doSearch")
    @Operation(summary = "Search Users", description = "Search users based on search term", tags = {
            "User CRUD operations" })
    @ApiResponse(responseCode = "200", description = "Users fetched successfully")
    public GenericResult searchUsers(@RequestBody JSONObject requestData) {
        LOG.info("Searching users :: " + requestData.get("searchTerm"));
        GenericResult result = new GenericResult();
        List<UserDto> users = new ArrayList<>();
        try {
            String searchType = requestData.get("searchType") != null ? requestData.get("searchType").toString() : "";
            if ("All".equalsIgnoreCase(searchType)) {
                users.addAll(userService.findByFirstName(requestData.get("name").toString()));
                users.addAll(userService.findByLastName(requestData.get("name").toString()));
                users.addAll(userService.findBySsn(requestData.get("name").toString()));
            } else if ("Fname".equalsIgnoreCase(searchType)) {
                users.addAll(userService.findByFirstName(requestData.get("name").toString()));
            } else if ("Lname".equalsIgnoreCase(searchType)) {
                users.addAll(userService.findByLastName(requestData.get("name").toString()));
            } else if ("Ssn".equalsIgnoreCase(searchType)) {
                users.addAll(userService.findBySsn(requestData.get("name").toString()));
            } else {
                users = userService.findAllUser();
            }
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
            LOG.error("Error searching users :: " + e.getMessage());
        }
        return result;
    }

    @PostMapping("/findUserById")
    @Operation(summary = "Load User based on id", description = "Load users to grid based on user's id from DB", tags = {
            "User CRUD operations" })
    @ApiResponse(responseCode = "200", description = "Users fetched successfully")
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
                result.setSuccess(false);
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
