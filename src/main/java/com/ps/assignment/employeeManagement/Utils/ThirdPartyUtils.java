package com.ps.assignment.employeeManagement.Utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ps.assignment.employeeManagement.model.User;
import com.ps.assignment.employeeManagement.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Component
public class ThirdPartyUtils {

    @Autowired
    private UserRepository userRepository;

    // @PostConstruct
    public void init() {
        System.out.println("ThirdPartyUtils initialized");
        List<User> users = new ArrayList<>();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://dummyjson.com/users"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            String jsonResponse = response.body();
            JSONObject jsonObject = new JSONObject(jsonResponse);

            if (jsonObject != null && jsonObject.has("users")) {
                JSONArray usersArray = (JSONArray) jsonObject.get("users");
                ObjectMapper objectMapper = new ObjectMapper();
                for (int i = 0; i < usersArray.length(); i++) {
                    JSONObject jsonUser = (JSONObject) usersArray.get(i);
                    User user = objectMapper.readValue(jsonUser.toString(), User.class);
                    users.add(user);
                }
                userRepository.saveAll(users);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Total users :: " + users.size());
        }
    }

    public List<User> mapUsers() {

        return null;
    }

}
