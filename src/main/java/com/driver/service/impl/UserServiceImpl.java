package com.driver.service.impl;

import com.driver.io.entity.UserEntity;
import com.driver.io.repository.UserRepository;
import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.UserResponse;
import com.driver.service.UserService;
import com.driver.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto user) throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setUserId(user.getUserId());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        UserEntity savedUser = userRepository.save(userEntity);

        UserDto userDto = new UserDto();
        userDto.setEmail(savedUser.getEmail());
        userDto.setId(savedUser.getId());
        userDto.setFirstName(savedUser.getFirstName());
        userDto.setUserId(savedUser.getUserId());
        userDto.setLastName(savedUser.getLastName());
        return userDto;
    }

    @Override
    public UserDto getUser(String email) throws Exception {
        UserEntity userEntity = userRepository.findByEmail(email);
        UserDto userDto = new UserDto();
        userDto.setEmail(userEntity.getEmail());
        userDto.setId(userEntity.getId());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setUserId(userEntity.getUserId());
        userDto.setLastName(userEntity.getLastName());
        return userDto;
    }

    @Override
    public UserDto getUserByUserId(String userId) throws Exception {

        Optional<UserEntity> optionalUser = Optional.ofNullable(userRepository.findByUserId(userId));
        UserResponse userResponse = new UserResponse();
        UserEntity userEntity = optionalUser.get();
        UserDto userDto = new UserDto();
        userDto.setEmail(userEntity.getEmail());
        userDto.setId(userEntity.getId());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setUserId(userEntity.getUserId());
        userDto.setLastName(userEntity.getLastName());



        return userDto;
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) throws Exception {

        UserEntity userEntity = userRepository.findByUserId(userId);

        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setFirstName(user.getFirstName());
        UserEntity updatedUdser = userRepository.save(userEntity);

        UserDto userDto = new UserDto();
        userDto.setLastName(updatedUdser.getLastName());
        userDto.setId(updatedUdser.getId());
        userDto.setUserId(updatedUdser.getUserId());
        userDto.setEmail(updatedUdser.getEmail());
        userDto.setFirstName(updatedUdser.getFirstName());
        return userDto;
    }

    @Override
    public void deleteUser(String userId) throws Exception {

        userRepository.deleteByUserId(userId);

    }

    @Override
    public List<UserDto> getUsers() {
        List<UserEntity> list = (List<UserEntity>) userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for(UserEntity userEntity : list)
        {
            UserDto userDto = new UserDto();
            userDto.setLastName(userEntity.getLastName());
            userDto.setId(userEntity.getId());
            userDto.setUserId(userEntity.getUserId());
            userDto.setEmail(userEntity.getEmail());
            userDto.setFirstName(userEntity.getFirstName());
            userDtoList.add(userDto);
        }
        return userDtoList;
    }
}