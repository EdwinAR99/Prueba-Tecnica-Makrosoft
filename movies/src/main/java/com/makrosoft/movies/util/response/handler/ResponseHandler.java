package com.makrosoft.movies.util.response.handler;

import com.makrosoft.movies.util.response.Response;

import lombok.Getter;

@Getter
public class ResponseHandler<T> {
    private final Response<T> response;

    public ResponseHandler(int status, String userMessage, String developerMessage, T data) {
        response = new Response<>();

        response.setStatus(status);
        response.setUserMessage(userMessage);
        response.setDeveloperMessage(developerMessage);
        response.setData(data);
    }
}
