package com.ps.assignment.employeeManagement.controller;

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
    public ResponseEntity<List<User>> findByFirstName(@RequestBody JSONObject requestData) {
        LOG.info("Finding users by first name :: " + requestData.get("firstName"));
        List<User> users = userService.findByFirstName(requestData.get("firstName").toString());
        return users != null ? ResponseEntity.ok(users) : ResponseEntity.badRequest().body(null);
    }

    @PostMapping("/findByLastName")
    public ResponseEntity<List<User>> findByLastName(@RequestBody String lastName) {
        LOG.info("Finding users by last name :: " + lastName);
        List<User> users = userService.findByLastName(lastName);
        return users != null ? ResponseEntity.ok(users) : ResponseEntity.badRequest().body(null);
    }

    @PostMapping("/doFreeSearch")
    public ResponseEntity<List<User>> doFreeSearch(@RequestBody JSONObject requestData) {
        LOG.info("Doing free search :: " + requestData.get("searchStr"));
        List<User> users = userService.doFreeSearch(requestData.get("searchStr").toString());
        return users != null ? ResponseEntity.ok(users) : ResponseEntity.badRequest().body(null);
    }
}
