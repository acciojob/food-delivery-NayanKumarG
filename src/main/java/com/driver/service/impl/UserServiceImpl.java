package com.driver.service.impl;

import com.driver.io.entity.UserEntity;
import com.driver.io.repository.UserRepository;
import com.driver.model.response.UserResponse;
import com.driver.service.UserService;
import com.driver.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto user) throws Exception {
        return null;
    }

    @Override
    public UserDto getUser(String email) throws Exception {
        return null;
    }

    @Override
    public UserResponse getUserByUserId(String userId) throws Exception {

        Optional<UserEntity> optionalUser = Optional.ofNullable(userRepository.findByUserId(userId));
        UserResponse userResponse = new UserResponse();
        UserEntity userEntity = optionalUser.get();

        userResponse.setUserId(userEntity.getUserId());
        userResponse.setEmail(userEntity.getEmail());
        userResponse.setFirstName(userEntity.getFirstName());
        userResponse.setLastName(userEntity.getLastName());

        return userResponse;
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) throws Exception {
        return null;
    }

    @Override
    public void deleteUser(String userId) throws Exception {

    }

    @Override
    public List<UserDto> getUsers() {
        return null;
    }
}