package com.ps.assignment.employeeManagement.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.ps.assignment.employeeManagement.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOG = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/loadUsers")
    public ResponseEntity<String> loadUsers() {
        LOG.info("Loading users from external API");
        Boolean status = false;
        JSONObject apiData = userService.getDataFromExternalAPI();
        if (apiData != null && apiData.has("users")) {
            JSONArray usersArray = (JSONArray) apiData.get("users");
            LOG.info("Total users found :: " + usersArray.length());
            status = userService.loadUsersFromExternalAPI(usersArray);
        } else {
            LOG.error("Failed to load users from external API");
        }
        return status ? ResponseEntity.ok("Users loaded successfully.")
                : ResponseEntity.badRequest().body("Failed to load users from external API.");
    }

}
