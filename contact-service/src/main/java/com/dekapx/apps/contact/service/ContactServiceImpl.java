package com.dekapx.apps.contact.service;

import com.dekapx.apps.contact.domain.Contact;
import com.dekapx.apps.contact.model.ContactModel;
import com.dekapx.apps.contact.repository.ContactRepository;
import com.dekapx.apps.contact.specification.ContactSpecification;
import com.dekapx.apps.core.mapper.Mapper;
import com.dekapx.apps.core.exception.ResourceAlreadyExistsException;
import com.dekapx.apps.core.exception.ResourceNotFoundException;
import com.dekapx.apps.core.mapper.MapperFactory;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
@Service
@Transactional
public class ContactServiceImpl implements ContactService {
    private MapperFactory mapperFactory;
    private ContactRepository repository;
    private Javers javers;
    private Mapper<Contact, ContactModel> mapper;

    @Autowired
    public ContactServiceImpl(final MapperFactory mapperFactory, final ContactRepository repository, final Javers javers) {
        this.mapperFactory = mapperFactory;
        this.repository = repository;
        this.javers = javers;
        this.mapper = this.mapperFactory.getMapper(Contact.class);
    }

    @Override
    public ContactModel findById(final Long id) {
        final var contact = findByIdFunction.apply(id);
        return this.mapper.toModel(contact);
    }

    @Override
    public List<ContactModel> findAll() {
        final List<ContactModel> contacts = new ArrayList<>();
        this.repository.findAll()
                .forEach(contact -> contacts.add(this.mapper.toModel(contact)));
        return contacts;
    }

    private Function<Long, Contact> findByIdFunction = (id) -> {
        final var contactOptional = this.repository.findById(id);
        return contactOptional.orElseThrow(
                () -> new ResourceNotFoundException(String.format("Contact with ID [%d] not found.", id)));
    };

    @Override
    public ContactModel findBySpecification(final Specification<Contact> specification) {
        final var contactOptional = this.repository.findOne(specification);
        final var contact = contactOptional.orElseThrow(
                () -> new ResourceNotFoundException("Contact not found for specification..."));
        return this.mapper.toModel(contact);
    }

    @Override
    public ContactModel save(final ContactModel dto) {
        var contact = this.mapper.toEntity(dto);
        if (this.repository.findOne(new ContactSpecification(contact)).isPresent()) {
            throw new ResourceAlreadyExistsException("Contact already exists...");
        }
        contact = this.repository.save(contact);
        log.debug("Contact created with ID [{}]", contact.getId());
        return this.mapper.toModel(contact);
    }

    @Override
    public ContactModel update(final ContactModel dto) {
        final var contact = findByIdFunction.apply(dto.getId());
        this.mapper.copyProperties(contact, dto);
        final var contactUpdated = this.repository.save(contact);
        log.debug("Contact updated with ID [{}]", contactUpdated.getId());
        return this.mapper.toModel(contactUpdated);
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
    public List<Shadow<Contact>> findShadows(final ContactModel contactDto) {
        final Optional<Contact> optional = this.repository.findById(contactDto.getId());
        final Contact contact = optional.orElseThrow(()
                -> new ResourceNotFoundException(String.format("Contact with ID [{}] not found...", contactDto.getId())));
        return this.javers.findShadows(QueryBuilder.byInstance(contact).build());
    }

    @Override
    public List<CdoSnapshot> findSnapshots(final ContactModel contactDto) {
        final Optional<Contact> optional = this.repository.findById(contactDto.getId());
        final Contact contact = optional.orElseThrow(()
                -> new ResourceNotFoundException(String.format("Contact with ID [{}] not found...", contactDto.getId())));
        return this.javers.findSnapshots(QueryBuilder.byInstance(contact).build());
    }

    @Override
    public Changes findChanges(final ContactModel contactDto) {
        final Optional<Contact> optional = this.repository.findById(contactDto.getId());
        final Contact contact = optional.orElseThrow(()
                -> new ResourceNotFoundException(String.format("Contact with ID [{}] not found...", contactDto.getId())));
        final Changes changes = this.javers.findChanges(QueryBuilder.byInstance(contact)
                .withChildValueObjects(true)
                .build());
        log.debug(changes.prettyPrint());
        return changes;
    }

    @Override
    public Changes findChanges() {
        final Changes changes = this.javers.findChanges(QueryBuilder.byClass(Contact.class)
                .withChildValueObjects()
                .build());
        log.debug(changes.prettyPrint());
        return changes;
    }
}
