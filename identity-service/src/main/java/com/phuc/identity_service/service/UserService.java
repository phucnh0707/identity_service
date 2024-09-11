package com.phuc.identity_service.service;

import com.phuc.identity_service.dto.request.UserCreationRequest;
import com.phuc.identity_service.dto.request.UserUpdateRequest;
import com.phuc.identity_service.entity.User;
import com.phuc.identity_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest requset) {
        User user = new User();
        user.setUsername(requset.getUsername());
        user.setFirstName(requset.getFirstName());
        user.setLastName(requset.getLastName());
        user.setPassword(requset.getPassword());
        user.setDob(requset.getDob());

        return userRepository.save(user);
    }

    public User updateUser(String userId, UserUpdateRequest request) {
        User user = getUser(userId);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());
        user.setDob(request.getDob());

        return userRepository.save(user);
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }

    public List<User> getUser() {
        return userRepository.findAll();
    }

    public User getUser(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


}
