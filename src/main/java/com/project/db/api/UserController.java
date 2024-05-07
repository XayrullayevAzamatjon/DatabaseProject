package com.project.db.api;

import com.project.db.entity.User;
import com.project.db.model.request.UserCreateRequest;
import com.project.db.model.request.UserUpdateRequest;
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
    public UserResponse addUser(@RequestBody UserCreateRequest userCreateRequest) {
        return userService.create(userCreateRequest);
    }

    @PutMapping("/update")
    public UserResponse updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.update(userUpdateRequest);
    }

    @GetMapping("/new-words/{Id}")
    public List<NewWordResponse> showNewWords(@PathVariable String Id){
        return userService.showNewWords(Id);
    }
}
