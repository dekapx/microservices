package com.dekapx.apps.contact.audit;

import com.dekapx.apps.contact.domain.Contact;
import com.dekapx.apps.contact.repository.ContactRepository;
import com.dekapx.apps.core.audit.AuditService;
import com.dekapx.apps.core.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.javers.core.Changes;
import org.javers.core.Javers;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.jql.QueryBuilder;
import org.javers.shadow.Shadow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ContactAuditService implements AuditService<Contact> {
    private final ContactRepository repository;
    private final Javers javers;

    @Autowired
    public ContactAuditService(final ContactRepository repository, final Javers javers) {
        this.repository = repository;
        this.javers = javers;
    }

    @Override
    public List<Shadow<Contact>> findShadows(final Long id) {
        final Optional<Contact> optional = this.repository.findById(id);
        final Contact contact = optional.orElseThrow(()
                -> new ResourceNotFoundException(String.format("Contact with ID [{}] not found...", id)));
        return this.javers.findShadows(QueryBuilder.byInstance(contact).build());
    }

    @Override
    public List<CdoSnapshot> findSnapshots(final Long id) {
        final Optional<Contact> optional = this.repository.findById(id);
        final Contact contact = optional.orElseThrow(()
                -> new ResourceNotFoundException(String.format("Contact with ID [{}] not found...", id)));
        return this.javers.findSnapshots(QueryBuilder.byInstance(contact).build());
    }

    @Override
    public Changes findChanges(final Long id) {
        final Optional<Contact> optional = this.repository.findById(id);
        final Contact contact = optional.orElseThrow(()
                -> new ResourceNotFoundException(String.format("Contact with ID [{}] not found...", id)));
        final Changes changes = this.javers.findChanges(QueryBuilder.byInstance(contact)
                .withChildValueObjects(true)
                .build());
        log.info(changes.prettyPrint());
        return changes;
    }

    @Override
    public Changes findChanges() {
        final Changes changes = this.javers
                .findChanges(QueryBuilder.byClass(Contact.class)
                        .withChildValueObjects()
                        .build());
        log.info(changes.prettyPrint());
        return changes;
    }
}
