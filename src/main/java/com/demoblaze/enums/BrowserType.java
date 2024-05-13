package com.demoblaze.enums;

public enum BrowserType {
    CHROME("chrome"),
    FIREFOX("firefox"),
    EDGE("edge");

    private final String key;

    BrowserType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public static BrowserType getByKey(String key) {
        for (BrowserType browserType : values()) {
            if (browserType.getKey().equalsIgnoreCase(key)) {
                return browserType;
            }
        }
        throw new IllegalArgumentException("Invalid browser key: " + key);
    }
}
