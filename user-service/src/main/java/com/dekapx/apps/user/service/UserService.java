package com.dekapx.apps.user.service;

import com.dekapx.apps.user.common.model.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();

    UserDto getUser(Long id);

    UserDto create(UserDto userDto);

    UserDto update(UserDto userDto);
}
