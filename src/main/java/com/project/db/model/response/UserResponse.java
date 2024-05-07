package com.project.db.model.response;

import java.time.LocalDateTime;
import com.project.db.utils.Role;

public record UserResponse(String Id, String firstName, String lastName, String email, Double
        score, Role Role, LocalDateTime joined_date) {
}
