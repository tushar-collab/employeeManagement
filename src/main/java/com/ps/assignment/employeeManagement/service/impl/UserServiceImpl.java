package com.ps.assignment.employeeManagement.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ps.assignment.employeeManagement.config.ExternalApiCaller;
import com.ps.assignment.employeeManagement.helper.UserHelper;
import com.ps.assignment.employeeManagement.model.Address;
import com.ps.assignment.employeeManagement.model.Bank;
import com.ps.assignment.employeeManagement.model.Company;
import com.ps.assignment.employeeManagement.model.Coordinates;
import com.ps.assignment.employeeManagement.model.Crypto;
import com.ps.assignment.employeeManagement.model.Hair;
import com.ps.assignment.employeeManagement.model.User;
import com.ps.assignment.employeeManagement.repository.UserRepository;
import com.ps.assignment.employeeManagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHelper userHelper;

    @Autowired
    private ExternalApiCaller externalApiCaller;

    @Value("${employeeManagement.startupConfigs.thirdPartyApi}")
    private String API_URL;

    @Override
    public JSONObject getDataFromExternalAPI() {
        LOG.info("In getDataFromExternalAPI()");
        JSONObject result = null;
        try {
            String response = externalApiCaller.callApi();
            result = new JSONObject(response);
        } catch (Exception e) {
            LOG.error("Error while fetching data from external API" + e.getMessage(), e);
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean loadUsersFromExternalAPI(JSONArray usersArray) {
        LOG.info("In loadUsersFromExternalAPI()");
        Boolean status = false;
        try {
            usersArray.forEach(jsonUser -> {
                String email = ((JSONObject) jsonUser).getString("email");
                Optional<User> user = userRepository.findByEmail(email);
                if (user.isPresent()) {
                    return;
                }
                JSONObject addressJson = (JSONObject) ((JSONObject) jsonUser).get("address");
                JSONObject coordinatesJson = (JSONObject) addressJson.get("coordinates");
                Coordinates coordinates = userHelper.findAndSaveCoordinates(coordinatesJson);
                Address address = userHelper.findAndSaveAddress(addressJson, coordinates);
                Crypto crypto = userHelper.findAndSaveCrypto((JSONObject) ((JSONObject) jsonUser).get("crypto"));
                Hair hair = userHelper.findAndSaveHair((JSONObject) ((JSONObject) jsonUser).get("hair"));
                Bank bank = userHelper.findAndSaveBank((JSONObject) ((JSONObject) jsonUser).get("bank"));
                Company company = userHelper
                        .findAndSaveCompany((JSONObject) ((JSONObject) jsonUser).get("company"));
                userHelper.createAndSaveUser((JSONObject) jsonUser, hair, address, bank, company, crypto);
            });
            status = true;
            LOG.info("Users saved successfully !!");
        } catch (Exception e) {
            LOG.error("Failed to fetch users from external API" + e.getMessage(), e);
        }
        return status;
    }

    @Override
    public List<User> findByFirstName(String firstName) {
        LOG.info("In findByFirstName()");
        Optional<List<User>> users = Optional.empty();
        try {
            users = userRepository.findByFirstName(firstName);
        } catch (Exception e) {
            LOG.error("Failed to fetch users from external API" + e.getMessage(), e);
        }
        return users.isPresent() ? users.get() : null;
    }

    @Override
    public List<User> findByLastName(String lastName) {
        LOG.info("In findByLastName()");
        Optional<List<User>> users = Optional.empty();
        try {
            users = userRepository.findByLastName(lastName);
        } catch (Exception e) {
            LOG.error("Failed to fetch users from external API" + e.getMessage(), e);
        }
        return users.isPresent() ? users.get() : null;
    }
}
