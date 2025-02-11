package com.ps.assignment.employeeManagement.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ps.assignment.employeeManagement.service.UserService;

import jakarta.annotation.PostConstruct;

@Component
public class DataLoaderHelper {

    @Autowired
    private UserService userService;

    private static final Logger LOG = LogManager.getLogger(DataLoaderHelper.class);

    @PostConstruct
    public void loadData() {
        try {
            JSONObject apiData = userService.getDataFromExternalAPI();
            if (apiData != null && apiData.has("users")) {
                JSONArray usersArray = (JSONArray) apiData.get("users");
                LOG.info("Total users found :: " + usersArray.length());
                userService.loadUsersFromExternalAPI(usersArray);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
