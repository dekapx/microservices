package com.dekapx.apps.contact.repository;

import com.dekapx.apps.contact.domain.Contact;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long>, JpaSpecificationExecutor<Contact> {
    <E extends Contact> E save(E entity);

    Contact findByFirstName(String firstName);
}
