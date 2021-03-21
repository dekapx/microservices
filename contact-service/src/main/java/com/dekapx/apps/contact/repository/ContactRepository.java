package com.dekapx.apps.contact.repository;

import com.dekapx.apps.contact.domain.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {
    <E extends Contact> E save(E entity);
}
