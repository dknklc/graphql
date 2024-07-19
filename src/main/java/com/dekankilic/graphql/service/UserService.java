package com.dekankilic.graphql.service;

import com.dekankilic.graphql.dto.UserRequest;
import com.dekankilic.graphql.exception.UserNotFoundException;
import com.dekankilic.graphql.model.User;
import com.dekankilic.graphql.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with given id not found " + id));
    }

    public User createUser(UserRequest userRequest) {
        User user = User.builder()
                .username(userRequest.getUsername())
                .mail(userRequest.getEmail())
                .role(userRequest.getRole())
                .build();
        return userRepository.save(user);
    }

    public User updateUser(UserRequest userRequest) {
        User existing = getUserById(userRequest.getId());
        existing.setRole(userRequest.getRole());
        existing.setUsername(userRequest.getUsername());
        existing.setMail(userRequest.getEmail());
        return userRepository.save(existing);
    }

    public Boolean deleteUser(Long id) {
        User existing = getUserById(id);
        userRepository.delete(existing);
        return true;
    }
}
