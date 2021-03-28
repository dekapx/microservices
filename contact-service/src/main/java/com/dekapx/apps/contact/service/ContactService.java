package com.dekapx.apps.contact.service;

import com.dekapx.apps.contact.domain.Contact;
import com.dekapx.apps.contact.model.ContactDto;
import org.springframework.data.jpa.domain.Specification;

public interface ContactService {
    ContactDto findById(Long id);
    ContactDto findBySpecification(Specification<Contact> specification);
    ContactDto save(ContactDto dto);
    void update(ContactDto dto);
    void delete(Long id);
}
