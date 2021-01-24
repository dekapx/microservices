package com.dekapx.apps.web.user.service;

import com.dekapx.apps.web.user.model.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();
    UserDto getUser(Long id);
    UserDto save(UserDto userDto);

    UserDto create(UserDto userDto);

    UserDto update(UserDto userDto);
}
