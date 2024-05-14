package com.demoblaze.enums;

public enum APIResponseStatus {
    SUCCESS(200), SUCCESS_CREATE(201);

    private final int code;

    APIResponseStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
