package com.dekapx.apps.web.user.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
}
