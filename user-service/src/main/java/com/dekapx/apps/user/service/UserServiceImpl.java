package com.dekapx.apps.user.service;

import com.dekapx.apps.user.common.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<UserModel> getUsers() {
        return List.of(
                UserModel.builder()
                        .firstName("De")
                        .lastName("Kapx")
                        .username("dekapx")
                        .email("dekapx@google.com")
                        .build(),
                UserModel.builder()
                        .firstName("Dummy")
                        .lastName("User")
                        .username("dummyuser")
                        .email("dummy.user@google.com")
                        .build());
    }

    @Override
    public UserModel getUser(final Long id) {
        return UserModel.builder()
                .firstName("Dummy")
                .lastName("User")
                .username("dummyuser")
                .email("dummy.user@google.com")
                .build();
    }

    @Override
    public UserModel create(final UserModel userModel) {
        return userModel;
    }

    @Override
    public UserModel update(UserModel userModel) {
        return userModel;
    }
}
