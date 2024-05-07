package com.project.db.model.request;

public record UserUpdateRequest(String Id, String firstName, String lastName, String email, String password) {

}
