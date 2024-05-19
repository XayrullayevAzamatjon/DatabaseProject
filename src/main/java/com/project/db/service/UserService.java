package com.project.db.service;

import com.project.db.entity.*;
import com.project.db.error.AlreadyExistsException;
import com.project.db.error.NotFoundException;
import com.project.db.model.request.LoginRequest;
import com.project.db.model.request.UserCreateRequest;
import com.project.db.model.request.UserUpdateRequest;
import com.project.db.model.response.LoginResponse;
import com.project.db.model.response.NewWordResponse;
import com.project.db.model.response.UserResponse;
import com.project.db.repository.UserRepository;
import com.project.db.utils.Role;
import com.project.db.utils.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(String Id) {

        return userRepository.findById(Id).orElseThrow(
                ()-> new NotFoundException("User Not Found with Id: " + Id));
    }

    public List<UserResponse> findAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(this::User2UserResponse)
                .toList();
    }

    public UserResponse create(UserCreateRequest userCreateRequest){
        if (userRepository.existsByEmail(userCreateRequest.email())) {
            LOGGER.error("User already exists with email: {}", userCreateRequest.email());
            throw new AlreadyExistsException("User with email " + userCreateRequest.email() + " already exists");
        }
        User user = CreateRequest2User(userCreateRequest);
        userRepository.save(user);
        LOGGER.info("User created with id {}", user.getId());
        return User2UserResponse(user);
    }

    public LoginResponse login(LoginRequest request) {
        boolean exists = userRepository.existsByEmail(request.email());
        if (exists){
            User user = userRepository
                    .findByEmail(request.email())
                    .orElseThrow(
                            () ->
                                    new NotFoundException("User Not Found with email: " + request.email())
                    );
            String password = user.getPassword();
            if (password.equals(request.password())){
                LOGGER.info("User logged in successfully with email [{}] and password[{}].", request.email(),request.password());
                return new LoginResponse("Email and password is correct",Boolean.TRUE);
            }
            else {
                LOGGER.info("User could not be logged in with email [{}] and password[{}].", request.email(),request.password());
                return new LoginResponse("Password is incorrect",Boolean.FALSE);
            }

        }
        else {
            LOGGER.info("User could not be logged in with email [{}].", request.email());
            return new LoginResponse("There is no user with email ["+request.email()+"]",Boolean.FALSE);
        }
    }

    public UserResponse update(UserUpdateRequest updateRequest){
        User user = findById(updateRequest.Id());
        user.setFirstName(updateRequest.firstName());
        user.setLastName(updateRequest.lastName());
        user.setEmail(updateRequest.email());
        user.setPassword(updateRequest.password());
        userRepository.save(user);
        LOGGER.info("User updated with id {}", user.getId());
        return User2UserResponse(user);
    }

    public List<NewWordResponse> userWordsByStatus(String Id, Status status){
        return findById(Id).getNewWordList().stream()
                .filter(newWord -> newWord.getStatus().equals(status))
                .map(this::NewWord2NewWordResponse)
                .toList();
    }

    public User CreateRequest2User(UserCreateRequest request){
        User user = new User();
        UUID uuid = UUID.randomUUID();
        user.setId(uuid.toString());
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setPassword(request.password());
        user.setRole(Role.USER);
        user.setScore(0L);
        LocalDateTime time = LocalDateTime.now();
        user.setJoinedDate(time);
        return user;
    }

    public UserResponse User2UserResponse(User user){
        return new UserResponse(
                user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getScore(), user.getRole(), user.getJoinedDate()
        );
    }

    public NewWordResponse NewWord2NewWordResponse(NewWord newWord){
        return new NewWordResponse(
                newWord.getWordId(), newWord.getUser().getId(), newWord.getWrittenForm(),
                newWord.getPartOfSpeech(), newWord.getDefinition(), newWord.getStatus(),
                newWord.getCreatedDate(), newWord.getConfirmedDate()
        );
    }

}
