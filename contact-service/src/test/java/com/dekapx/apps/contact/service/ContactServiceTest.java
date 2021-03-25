package com.dekapx.apps.contact.service;

import com.dekapx.apps.contact.model.ContactDto;
import com.dekapx.apps.core.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ContactServiceTest {
    private static final String FIRST_NAME = "Test";
    private static final String LAST_NAME = "User";
    private static final String EMAIL = "testuser@google.com";
    private static final String PHONE = "+1 123 456 7890";

    @Autowired
    private ContactService contactService;

    @Disabled
    @Test
    @DisplayName("Create Update & Delete Contact")
    public void createUpdateAndDelete() {
        this.contactService.save(contactSupplier.get());
    }

    @Disabled
    @Test
    @DisplayName("Find contact by ID")
    public void givenValidContactIdReturnContact() {
        ContactDto contactDto = this.contactService.findById(29L);
        assertAll(
                () -> assertNotNull(contactDto),
                () -> assertEquals(FIRST_NAME, contactDto.getFirstName()),
                () -> assertEquals(LAST_NAME, contactDto.getLastName()),
                () -> assertEquals(EMAIL, contactDto.getEmail()),
                () -> assertEquals(PHONE, contactDto.getPhone())
        );
    }

    @Test
    @DisplayName("Find Contact by ID Throw Exception")
    public void givenInvalidContactIdThrowException() {
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            this.contactService.findById(1L);
        });
        assertAll(
                () -> assertEquals("Contact with ID [1] not found.", exception.getMessage())
        );
    }


    private Supplier<ContactDto> contactSupplier = () ->
            ContactDto.builder()
                    .firstName(FIRST_NAME)
                    .lastName(LAST_NAME)
                    .email(EMAIL)
                    .phone(PHONE)
                    .build();
}
