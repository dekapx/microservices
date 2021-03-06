package com.dekapx.apps.user.common.model;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Value
@Builder
public class UserDto {
    @NotNull(message = "First Name cannot be null")
    private String firstName;

    @NotNull(message = "Last Name cannot be null")
    private String lastName;

    @NotNull(message = "Username cannot be null")
    @Size(min = 6, max = 20, message = "Username must be between 6 and 20 characters")
    private String username;

    @Email(message = "Email should be valid")
    private String email;
}
