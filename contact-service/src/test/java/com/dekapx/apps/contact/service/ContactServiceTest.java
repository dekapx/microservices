package com.dekapx.apps.contact.service;

import com.dekapx.apps.contact.domain.Contact;
import com.dekapx.apps.contact.model.ContactDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Supplier;

@SpringBootTest
public class ContactServiceTest {
    private static final String FIRST_NAME = "Test";
    private static final String LAST_NAME = "User";
    private static final String EMAIL = "testuser@google.com";
    private static final String PHONE = "+1 123 456 7890";

    @Autowired
    private ContactService contactService;

    @Test
    @DisplayName("Create Update & Delete Contact")
    public void createUpdateAndDelete() {
        this.contactService.save(contactSupplier.get());
    }

    private Supplier<ContactDto> contactSupplier = () ->
            ContactDto.builder()
                    .firstName(FIRST_NAME)
                    .lastName(LAST_NAME)
                    .email(EMAIL)
                    .phone(PHONE)
                    .build();
}
