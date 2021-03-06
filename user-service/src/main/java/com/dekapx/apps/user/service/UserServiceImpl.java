package com.dekapx.apps.user.service;

import com.dekapx.apps.user.common.model.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<UserDto> getUsers() {
        return List.of(UserDto.builder()
                .firstName("De")
                .lastName("Kapx")
                .username("dekapx")
                .email("dekapx@google.com")
                .build());
    }

    @Override
    public UserDto getUser(final Long id) {
        return UserDto.builder()
                .firstName("Dummy")
                .lastName("User")
                .username("dummyuser")
                .email("dummy.user@google.com")
                .build();
    }

    @Override
    public UserDto create(final UserDto userDto) {
        return userDto;
    }

    @Override
    public UserDto update(UserDto userDto) {
        return userDto;
    }
}
