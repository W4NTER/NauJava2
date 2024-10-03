package edu.hw1.http;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Headers {
    @JsonProperty("headers")
    private Map<String, String> headers;


    @Override
    public String toString() {
        return String.join(", ", headers.keySet());
    }
}
