package edu.hw1.http;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Ip (
        @JsonProperty("origin")
        String ip
) {
        @Override
        public String toString() {
                return ip;
        }
}
