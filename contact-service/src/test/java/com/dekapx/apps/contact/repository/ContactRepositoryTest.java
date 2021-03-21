package com.dekapx.apps.contact.repository;

import com.dekapx.apps.contact.domain.Contact;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ContactRepositoryTest {
    @Autowired
    private ContactRepository repository;


    @Test
    @DisplayName("")
    public void createAndVerifyContact() {
        Contact contact = this.repository.save(contactSupplier.get());
        assertThat(contact).isNotNull();
    }

    private Supplier<Contact> contactSupplier = () -> {
        Contact contact = new Contact();
        contact.setFirstName("Test");
        contact.setLastName("User");
        contact.setEmail("test@mydomain.com");
        contact.setPhone("+1 123 456 7890");
        return contact;
    };
}
