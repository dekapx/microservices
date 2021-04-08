package com.dekapx.apps.contact.service;

import com.dekapx.apps.contact.domain.Contact;
import com.dekapx.apps.contact.model.ContactModel;
import com.dekapx.apps.core.service.CrudService;
import org.javers.core.Changes;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.shadow.Shadow;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ContactService extends CrudService<ContactModel, Contact> {
    List<Shadow<Contact>> findShadows(ContactModel model);

    List<CdoSnapshot> findSnapshots(ContactModel model);

    Changes findChanges(ContactModel model);

    Changes findChanges();
}
