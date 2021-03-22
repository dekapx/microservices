package com.dekapx.apps.contact.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
