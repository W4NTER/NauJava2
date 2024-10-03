package edu.hw1.http;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Anything(
        @JsonProperty("Accept")
        String accept
) {
        @Override
        public String toString() {
                return accept;
        }
}
