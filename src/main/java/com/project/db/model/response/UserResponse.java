package com.project.db.model.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.db.utils.Role;

public record UserResponse(
        @JsonProperty(namespace = "id") String Id,
        @JsonProperty(namespace = "first_name") String firstName,
        @JsonProperty(namespace = "last_name") String lastName,
        @JsonProperty(namespace = "email") String email,
        @JsonProperty(namespace = "score") Long score,
        @JsonProperty(namespace = "role") Role role,
        @JsonProperty(namespace = "joined_date") LocalDateTime joinedDate) {
}
