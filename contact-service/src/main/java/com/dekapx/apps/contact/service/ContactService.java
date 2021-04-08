package com.dekapx.apps.contact.service;

import com.dekapx.apps.contact.domain.Contact;
import com.dekapx.apps.contact.model.ContactModel;
import org.javers.core.Changes;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.shadow.Shadow;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ContactService {
    ContactModel findById(Long id);
    List<ContactModel> findAll();
    ContactModel findBySpecification(Specification<Contact> specification);
    ContactModel save(ContactModel dto);
    ContactModel update(ContactModel dto);
    void delete(Long id);

    List<Shadow<Contact>> findShadows(ContactModel contactDto);
    List<CdoSnapshot> findSnapshots(ContactModel contactDto);
    Changes findChanges(ContactModel contactDto);
    Changes findChanges();
}
