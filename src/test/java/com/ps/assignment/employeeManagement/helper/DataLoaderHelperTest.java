package com.ps.assignment.employeeManagement.helper;

import static org.mockito.Mockito.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ps.assignment.employeeManagement.service.UserService;

@ExtendWith(MockitoExtension.class)
public class DataLoaderHelperTest {

	@Mock
	private UserService userService;

	@InjectMocks
	private DataLoaderHelper dataLoaderHelper;

	private JSONObject apiData;
	private JSONArray usersArray;

	@BeforeEach
	public void setUp() throws JSONException {
        usersArray = new JSONArray();
        usersArray.put(new JSONObject().put("id", 1).put("name", "John Doe"));
        usersArray.put(new JSONObject().put("id", 2).put("name", "Jane Doe"));

        apiData = new JSONObject();
        apiData.put("users", usersArray);
    }

    @Test
    public void testLoadData_Success() throws Exception {
        when(userService.getDataFromExternalAPI()).thenReturn(apiData);

        dataLoaderHelper.loadData();

        verify(userService, times(1)).getDataFromExternalAPI();
        verify(userService, times(1)).loadUsersFromExternalAPI(usersArray);
    }

    @Test
    public void testLoadData_NoUsers() throws Exception {
        JSONObject emptyApiData = new JSONObject();
        when(userService.getDataFromExternalAPI()).thenReturn(emptyApiData);

        dataLoaderHelper.loadData();

        verify(userService, times(1)).getDataFromExternalAPI();
        verify(userService, never()).loadUsersFromExternalAPI(any(JSONArray.class));
    }

    @Test
    public void testLoadData_Exception() throws Exception {
        when(userService.getDataFromExternalAPI()).thenThrow(new RuntimeException("API error"));

        dataLoaderHelper.loadData();

        verify(userService, times(1)).getDataFromExternalAPI();
        verify(userService, never()).loadUsersFromExternalAPI(any(JSONArray.class));
    }
}