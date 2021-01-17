package com.dekapx.apps.web.user.controller;

import com.dekapx.apps.web.user.model.UserDto;
import com.dekapx.apps.web.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/users", produces = "application/json")
    private List<UserDto> getUsers() {
        log.debug("Return all users...");
        return this.userService.getUsers();
    }

    @GetMapping(value = "/users/{id}", produces = "application/json")
    private UserDto getUser(@PathVariable Long id) {
        log.debug("Return all users...");
        return this.userService.getUser(id);
    }
}
