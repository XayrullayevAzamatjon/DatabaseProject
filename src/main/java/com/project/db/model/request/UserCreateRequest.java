package com.project.db.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserCreateRequest(
        @JsonProperty(namespace = "first_name") String firstName,
        @JsonProperty(namespace = "last_name") String lastName,
        @JsonProperty(namespace = "email") String email,
        @JsonProperty(namespace = "password") String password) {
}
