package com.dekapx.apps.contact.service;

import com.dekapx.apps.contact.domain.Contact;
import com.dekapx.apps.contact.model.ContactDto;
import org.javers.core.Changes;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.shadow.Shadow;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ContactService {
    ContactDto findById(Long id);
    List<ContactDto> findAll();
    ContactDto findBySpecification(Specification<Contact> specification);
    ContactDto save(ContactDto dto);
    ContactDto update(ContactDto dto);
    void delete(Long id);

    List<Shadow<Contact>> findShadows(ContactDto contactDto);
    List<CdoSnapshot> findSnapshots(ContactDto contactDto);
    Changes findChanges(ContactDto contactDto);
    Changes findChanges();
}
