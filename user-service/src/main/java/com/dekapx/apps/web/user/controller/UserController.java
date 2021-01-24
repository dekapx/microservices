package com.dekapx.apps.web.user.controller;

import com.dekapx.apps.web.user.model.UserDto;
import com.dekapx.apps.web.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDto>> getUsers() {
        log.debug("Return all users...");
        final List<UserDto> userDtos = this.userService.getUsers();
        return new ResponseEntity<List<UserDto>>(userDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        log.debug("Return all users...");
        final UserDto userDto = this.userService.getUser(id);
        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
    }

    @PostMapping(value = "/users/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> create(@RequestBody UserDto userRequest) {
        log.debug("Create user...");
        final UserDto userResponse = this.userService.create(userRequest);
        return new ResponseEntity<UserDto>(userResponse, HttpStatus.OK);
    }

    @PutMapping(value = "/users/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> update(@RequestBody UserDto userRequest) {
        log.debug("Update user...");
        final UserDto userResponse = this.userService.update(userRequest);
        return new ResponseEntity<UserDto>(userResponse, HttpStatus.OK);
    }
}
