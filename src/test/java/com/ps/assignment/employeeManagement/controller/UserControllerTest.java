package com.ps.assignment.employeeManagement.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import com.ps.assignment.employeeManagement.dto.GenericResult;
import com.ps.assignment.employeeManagement.dto.UserDto;
import com.ps.assignment.employeeManagement.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	@Mock
	private UserService userService;

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private UserController userController;

	private JSONObject requestData;
	private UserDto userDto;
	private List<UserDto> userList;

	@SuppressWarnings("unchecked")
	@BeforeEach
	public void setUp() {
		requestData = new JSONObject();
		requestData.put("name", "John");
		requestData.put("ssn", "123-45-6789");
		requestData.put("id", 1);

		userDto = new UserDto();
		userDto.setId(1L);
		userDto.setFirstName("John");
		userDto.setLastName("Doe");

		userList = new ArrayList<>();
		userList.add(userDto);
	}

    @Test
    public void testFindAllUsers() {
        when(userService.findAllUser()).thenReturn(userList);

        GenericResult result = userController.findAllUsers();
        assertNotNull(result);
        assertTrue(result.getSuccess());
        assertEquals("SUCCESS", result.getStatus());
        assertEquals(userList, result.getData());

        verify(userService, times(1)).findAllUser();
    }

    @Test
    public void testFindByFirstName() {
        when(userService.findByFirstName(anyString())).thenReturn(userList);

        GenericResult result = userController.findByFirstName(requestData);
        assertNotNull(result);
        assertTrue(result.getSuccess());
        assertEquals("SUCCESS", result.getStatus());
        assertEquals(userList, result.getData());

        verify(userService, times(1)).findByFirstName(anyString());
    }

    @Test
    public void testFindByLastName() {
        when(userService.findByLastName(anyString())).thenReturn(userList);

        GenericResult result = userController.findByLastName(requestData);
        assertNotNull(result);
        assertTrue(result.getSuccess());
        assertEquals("SUCCESS", result.getStatus());
        assertEquals(userList, result.getData());

        verify(userService, times(1)).findByLastName(anyString());
    }

    @Test
    public void testFindBySsn() {
        when(userService.findBySsn(anyString())).thenReturn(userList);

        GenericResult result = userController.findBySsn(requestData);
        assertNotNull(result);
        assertTrue(result.getSuccess());
        assertEquals("SUCCESS", result.getStatus());
        assertEquals(userList, result.getData());

        verify(userService, times(1)).findBySsn(anyString());
    }

    @Test
    public void testDoFreeSearch() {
        when(userService.findByFirstName(anyString())).thenReturn(userList);
        when(userService.findByLastName(anyString())).thenReturn(userList);
        when(userService.findBySsn(anyString())).thenReturn(userList);

        GenericResult result = userController.doFreeSearch(requestData);
        assertNotNull(result);
        assertTrue(result.getSuccess());
        assertEquals("SUCCESS", result.getStatus());
        assertEquals(userList.size() * 3, ((List<UserDto>) result.getData()).size());

        verify(userService, times(1)).findByFirstName(anyString());
        verify(userService, times(1)).findByLastName(anyString());
        verify(userService, times(1)).findBySsn(anyString());
    }

    @Test
    public void testFindUserById() {
        when(userService.findUserById(anyInt())).thenReturn(userDto);

        GenericResult result = userController.findUserById(requestData);
        assertNotNull(result);
        assertTrue(result.getSuccess());
        assertEquals("SUCCESS", result.getStatus());
        assertEquals(userDto, result.getData());

        verify(userService, times(1)).findUserById(anyInt());
    }
}