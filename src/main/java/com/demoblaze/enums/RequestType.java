package com.demoblaze.enums;

public enum RequestType {
        POST("POST"), GET("GET"), PUT("PUT"), DELETE("DELETE"), PATCH("PATCH");
        private final String value;

        RequestType(String type) {
            this.value = type;
        }

        public String getValue() {
            return value;
        }
    }