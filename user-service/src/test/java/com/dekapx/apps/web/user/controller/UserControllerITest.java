package com.dekapx.apps.web.user.controller;

import com.dekapx.apps.web.user.model.UserDto;
import com.dekapx.apps.web.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.function.Supplier;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerITest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void shouldReturnAllUsers() throws Exception {
        when(userService.getUsers()).thenReturn(usersSupplier.get());
        this.mockMvc.perform(get("/api/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is("De")))
                .andExpect(jsonPath("$[0].lastName", is("Kapx")))
                .andExpect(jsonPath("$[0].username", is("dekapx")))
                .andExpect(jsonPath("$[0].email", is("dekapx@google.com")));
    }

    @Test
    public void shouldReturnUserById() throws Exception {
        when(userService.getUser(1L)).thenReturn(getUser());
        this.mockMvc.perform(get("/api/users/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("De")))
                .andExpect(jsonPath("$.lastName", is("Kapx")))
                .andExpect(jsonPath("$.username", is("dekapx")))
                .andExpect(jsonPath("$.email", is("dekapx@google.com")));
    }

    private Supplier<List<UserDto>> usersSupplier = () -> List.of(getUser());

    private UserDto getUser() {
        return UserDto.builder()
                .firstName("De")
                .lastName("Kapx")
                .username("dekapx")
                .email("dekapx@google.com")
                .build();
    }
}
