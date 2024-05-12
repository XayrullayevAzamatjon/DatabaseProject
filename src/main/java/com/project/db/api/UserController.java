package com.project.db.api;

import com.project.db.entity.User;
import com.project.db.model.request.LoginRequest;
import com.project.db.model.request.UserCreateRequest;
import com.project.db.model.request.UserUpdateRequest;
import com.project.db.model.response.LoginResponse;
import com.project.db.model.response.NewWordResponse;
import com.project.db.model.response.UserResponse;
import com.project.db.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserResponse findUserById(@PathVariable String id) {
        User user = userService.findById(id);
        return userService.User2UserResponse(user);
    }

    @GetMapping()
    public List<UserResponse> findAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping()
    public UserResponse create(@RequestBody UserCreateRequest userCreateRequest) {
        return userService.create(userCreateRequest);
    }
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @PutMapping("/update")
    public UserResponse updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.update(userUpdateRequest);
    }

    @GetMapping("/new-words/{userId}")
    public List<NewWordResponse> showNewWords(@PathVariable String userId){
        return userService.showNewWords(userId);
    }
}
