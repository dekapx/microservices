package com.dekapx.apps.contact.service;

import com.dekapx.apps.contact.domain.Contact;
import com.dekapx.apps.contact.model.ContactDto;
import com.dekapx.apps.contact.repository.ContactRepository;
import com.dekapx.apps.contact.specification.ContactSpecification;
import com.dekapx.apps.core.convertor.Convertor;
import com.dekapx.apps.core.exception.ResourceAlreadyExistsException;
import com.dekapx.apps.core.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.javers.core.Changes;
import org.javers.core.Javers;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.jql.QueryBuilder;
import org.javers.shadow.Shadow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class ContactServiceImpl implements ContactService {
    @Autowired
    @Qualifier("contactConvertor")
    private Convertor<Contact, ContactDto> convertor;

    @Autowired
    private ContactRepository repository;

    @Autowired
    private Javers javers;

    @Override
    public ContactDto findById(final Long id) {
        final var contactOptional = this.repository.findById(id);
        final var contact = contactOptional.orElseThrow(
                () -> new ResourceNotFoundException(String.format("Contact with ID [%d] not found.", id)));
        return this.convertor.toDto(contact);
    }

    @Override
    public ContactDto findBySpecification(final Specification<Contact> specification) {
        final var contactOptional = this.repository.findOne(specification);
        final var contact = contactOptional.orElseThrow(
                () -> new ResourceNotFoundException("Contact not found for specification..."));
        return this.convertor.toDto(contact);
    }

    @Override
    public ContactDto save(final ContactDto dto) {
        var contact = this.convertor.toEntity(dto);
        if(this.repository.findOne(new ContactSpecification(contact)).isPresent()){
            throw new ResourceAlreadyExistsException("Contact already exists...");
        }
        contact = this.repository.save(contact);
        log.debug("Contact created with ID [{}]", contact.getId());
        return this.convertor.toDto(contact);
    }

    @Override
    public ContactDto update(final ContactDto dto) {
        final var contact = this.convertor.toEntity(dto);
        final var contactUpdated = this.repository.save(contact);
        log.debug("Contact updated with ID [{}]", contactUpdated.getId());
        return this.convertor.toDto(contactUpdated);
    }

    @Override
    public void delete(final Long id) {
        final var contactOptional = this.repository.findById(id);
        final var contact = contactOptional.orElseThrow(()
                -> new ResourceNotFoundException(String.format("Contact with ID [%d] not found.", id)));
        this.repository.delete(contact);
        log.debug("Contact with ID [{}}] is deleted successfully... ", id);
    }

    @Override
    public List<Shadow<Contact>> findShadows(final ContactDto contactDto) {
        final Optional<Contact> optional = this.repository.findById(contactDto.getId());
        final Contact contact = optional.orElseThrow(()
                -> new ResourceNotFoundException(String.format("Contact with ID [{}] not found...", contactDto.getId())));
        return this.javers.findShadows(QueryBuilder.byInstance(contact).build());
    }

    @Override
    public List<CdoSnapshot> findSnapshots(final ContactDto contactDto) {
        final Optional<Contact> optional = this.repository.findById(contactDto.getId());
        final Contact contact = optional.orElseThrow(()
                -> new ResourceNotFoundException(String.format("Contact with ID [{}] not found...", contactDto.getId())));
        return this.javers.findSnapshots(QueryBuilder.byInstance(contact).build());
    }

    @Override
    public Changes findChanges(final ContactDto contactDto) {
        final Optional<Contact> optional = this.repository.findById(contactDto.getId());
        final Contact contact = optional.orElseThrow(()
                -> new ResourceNotFoundException(String.format("Contact with ID [{}] not found...", contactDto.getId())));
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
