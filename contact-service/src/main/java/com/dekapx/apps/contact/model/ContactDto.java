package com.dekapx.apps.contact.model;

import com.dekapx.apps.core.model.BaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Digits;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ContactDto extends BaseDto {
    @NotNull(message = "First Name cannot be null")
    @Size(min = 2, max = 20, message = "First Name must be between 2 and 20 characters")
    private String firstName;

    @NotNull(message = "Last Name cannot be null")
    @Size(min = 2, max = 20, message = "Last Name must be between 2 and 20 characters")
    private String lastName;

    @Email(message = "Email should be valid")
    private String email;

    @Digits(message = "Phone must contains numbers only", integer = 10, fraction = 0)
    private String phone;
}
