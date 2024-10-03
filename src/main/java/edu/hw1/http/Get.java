package edu.hw1.http;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Get(
        @JsonProperty("Host")
        String host
) {
        @Override
        public String toString() {
                return host;
        }
}
