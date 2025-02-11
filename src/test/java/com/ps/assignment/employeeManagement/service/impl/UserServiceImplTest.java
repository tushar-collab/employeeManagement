package com.ps.assignment.employeeManagement.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ps.assignment.employeeManagement.config.ExternalApiCaller;
import com.ps.assignment.employeeManagement.dto.UserDto;
import com.ps.assignment.employeeManagement.helper.UserHelper;
import com.ps.assignment.employeeManagement.model.*;
import com.ps.assignment.employeeManagement.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private UserHelper userHelper;

	@Mock
	private ExternalApiCaller externalApiCaller;

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	private JSONObject apiData;
	private JSONArray usersArray;
	private User user;
	private UserDto userDto;

	@BeforeEach
	public void setUp() throws Exception {
        usersArray = new JSONArray();
        JSONObject jsonUser = new JSONObject();
        jsonUser.put("email", "john.doe@example.com");
        jsonUser.put("address", new JSONObject().put("coordinates", new JSONObject().put("lat", 40.7128).put("lng", -74.0060)));
        jsonUser.put("crypto", new JSONObject().put("coin", "Bitcoin").put("wallet", "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa").put("network", "BTC"));
        jsonUser.put("hair", new JSONObject().put("color", "Brown").put("type", "Curly"));
        jsonUser.put("bank", new JSONObject().put("cardExpire", "12/25").put("cardNumber", "1234567890123456").put("cardType", "Visa").put("currency", "USD").put("iban", "US1234567890123456"));
        jsonUser.put("company", new JSONObject().put("name", "Tech Corp").put("department", "Engineering").put("title", "Software Engineer").put("address", new JSONObject().put("coordinates", new JSONObject().put("lat", 40.7128).put("lng", -74.0060))));
        usersArray.put(jsonUser);

        apiData = new JSONObject();
        apiData.put("users", usersArray);

        user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");

        userDto = new UserDto();
        userDto.setId(1L);
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setEmail("john.doe@example.com");
    }

    @Test
    public void testGetDataFromExternalAPI() {
        when(externalApiCaller.callApi()).thenReturn(apiData.toString());

        JSONObject result = userServiceImpl.getDataFromExternalAPI();
        assertNotNull(result);
        assertTrue(result.has("users"));

        verify(externalApiCaller, times(1)).callApi();
    }

    @Test
    public void testFindAllUser() {
        List<Object[]> result = new ArrayList<>();
        result.add(new Object[]{1L, "John", "Doe", "Smith", "john.doe@example.com", "123-456-7890", "Tech Corp", 30, "123-45-6789"});
        when(userRepository.fetchUsers()).thenReturn(result);

        List<UserDto> users = userServiceImpl.findAllUser();
        assertNotNull(users);
        assertEquals(1, users.size());

        verify(userRepository, times(1)).fetchUsers();
    }

    @Test
    public void testFindByFirstName() {
        List<Object[]> result = new ArrayList<>();
        result.add(new Object[]{1L, "John", "Doe", "Smith", "john.doe@example.com", "123-456-7890", "Tech Corp", 30, "MIT"});
        when(userRepository.findUsersByFirstName(anyString())).thenReturn(Optional.of(result));

        List<UserDto> users = userServiceImpl.findByFirstName("John");
        assertNotNull(users);
        assertEquals(1, users.size());

        verify(userRepository, times(1)).findUsersByFirstName(anyString());
    }

    @Test
    public void testFindByLastName() {
        List<Object[]> result = new ArrayList<>();
        result.add(new Object[]{1L, "John", "Doe", "Smith", "john.doe@example.com", "123-456-7890", "Tech Corp", 30, "MIT"});
        when(userRepository.findUsersByLastName(anyString())).thenReturn(Optional.of(result));

        List<UserDto> users = userServiceImpl.findByLastName("Doe");
        assertNotNull(users);
        assertEquals(1, users.size());

        verify(userRepository, times(1)).findUsersByLastName(anyString());
    }

    @Test
    public void testFindBySsn() {
        List<Object[]> result = new ArrayList<>();
        result.add(new Object[]{1L, "John", "Doe", "Smith", "john.doe@example.com", "123-456-7890", "Tech Corp", 30, "MIT"});
        when(userRepository.findUsersBySsn(anyString())).thenReturn(Optional.of(result));

        List<UserDto> users = userServiceImpl.findBySsn("123-45-6789");
        assertNotNull(users);
        assertEquals(1, users.size());

        verify(userRepository, times(1)).findUsersBySsn(anyString());
    }

    @Test
    public void testFindUserById() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));

        UserDto result = userServiceImpl.findUserById(1);
        assertNotNull(result);
        assertEquals("John", result.getFirstName());

        verify(userRepository, times(1)).findById(anyInt());
    }
}