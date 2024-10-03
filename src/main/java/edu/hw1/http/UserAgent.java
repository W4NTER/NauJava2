package edu.hw1.http;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserAgent(
        @JsonProperty("user-agent")
        String userAgent
) {
        @Override
        public String toString() {
                return userAgent;
        }
}
