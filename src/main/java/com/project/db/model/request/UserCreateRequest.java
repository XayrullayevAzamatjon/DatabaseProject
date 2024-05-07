package com.project.db.model.request;

public record UserCreateRequest(String firstName, String lastName, String email, String password) {

}
