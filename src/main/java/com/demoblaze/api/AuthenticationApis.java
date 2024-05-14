package com.demoblaze.api;

import com.demoblaze.enums.RequestType;
import com.demoblaze.utils.actions.ApiActions;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import static com.demoblaze.enums.APIResponseStatus.SUCCESS;

public class AuthenticationApis {
    private final ApiActions apiActions;

    private final String signup_serviceName = "signup";

    public AuthenticationApis() {
        this.apiActions = new ApiActions();
    }

    @Step("Register user")
    public Response registerUser(String username, String password) {
        String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());

        HashMap<String,String> createPostBody = new HashMap<>();
        createPostBody.put("username", username);
        createPostBody.put("password", encodedPassword);

        Map<String, Object> createPostHeaders = new HashMap<>();
        createPostHeaders.put("charset", "UTF-8");

        return apiActions.performRequest(
                RequestType.POST,
                signup_serviceName,
                SUCCESS.getCode(),
                createPostHeaders,
                ContentType.JSON, null, null, createPostBody, null);
    }


}

