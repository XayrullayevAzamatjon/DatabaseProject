package com.project.db.model.response;

import java.time.LocalDateTime;

public record UserResponse(String Id, String firstName, String lastName, String email, Double
        score, com.project.db.utils.Role Role, LocalDateTime joined_date) {
}
