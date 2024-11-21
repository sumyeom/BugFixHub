package com.example.bugfixhub.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PostType {
    INFO("info"),
    ASK("ask");

    private final String value;

    PostType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static PostType fromValue(String value) {
        for (PostType type : PostType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value for PostType: " + value);
    }
}
