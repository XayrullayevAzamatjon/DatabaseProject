package com.project.db.service;

import com.project.db.entity.NewWord;
import com.project.db.entity.User;
import com.project.db.model.request.UserCreateRequest;
import com.project.db.model.request.UserUpdateRequest;
import com.project.db.model.response.UserResponse;
import com.project.db.repositpry.UserRepository;
import com.project.db.utils.Role;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(String Id) {

        return userRepository.findById(Id).orElseThrow(
                ()-> new RuntimeException("User Not Found"));
    }

    public List<UserResponse> findAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(this::User2UserResponse)
                .toList();
    }

    public UserResponse create(UserCreateRequest userCreateRequest){
        User user = CreateRequest2User(userCreateRequest);
        userRepository.save(user);
        return User2UserResponse(user);
    }

    public UserResponse update(UserUpdateRequest userUpdateRequest){
        User user = findById(userUpdateRequest.Id());
        user.setFirstName(userUpdateRequest.firstName());
        user.setLastName(userUpdateRequest.lastName());
        user.setEmail(userUpdateRequest.email());
        user.setPassword(userUpdateRequest.password());
        userRepository.save(user);
        return User2UserResponse(user);
    }

    public List<NewWord> showNewWords(String Id){
        User user = findById(Id);
        return user.getNewWordList();
    }

    public User CreateRequest2User(UserCreateRequest userCreateRequest){
        User user = new User();
        UUID uuid = UUID.randomUUID();
        user.setId(uuid.toString());
        user.setFirstName(userCreateRequest.firstName());
        user.setLastName(userCreateRequest.lastName());
        user.setEmail(userCreateRequest.email());
        user.setPassword(userCreateRequest.password());
        user.setRole(Role.USER);
        user.setScore(0D);
        LocalDateTime time = LocalDateTime.now();
        user.setJoined_date(time);
        return user;
    }

    public UserResponse User2UserResponse(User user){
        return new UserResponse(
                user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getScore(), user.getRole(), user.getJoined_date()
        );
    }
}
