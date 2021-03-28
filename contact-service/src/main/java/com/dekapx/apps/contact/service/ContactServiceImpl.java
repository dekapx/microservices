package com.dekapx.apps.contact.service;

import com.dekapx.apps.contact.domain.Contact;
import com.dekapx.apps.contact.mapper.ContactMapper;
import com.dekapx.apps.contact.model.ContactDto;
import com.dekapx.apps.contact.repository.ContactRepository;
import com.dekapx.apps.core.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class ContactServiceImpl implements ContactService {
    private ContactMapper mapper;
    private ContactRepository repository;

    @Autowired
    public ContactServiceImpl(final ContactMapper mapper, final ContactRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public ContactDto findById(final Long id) {
        final var contactOptional = this.repository.findById(id);
        final var contact = contactOptional.orElseThrow(
                () -> new ResourceNotFoundException(String.format("Contact with ID [%d] not found.", id)));
        return this.mapper.toContactDto(contact);
    }

    @Override
    public ContactDto findBySpecification(final Specification<Contact> specification) {
        final var contactOptional = this.repository.findOne(specification);
        final var contact = contactOptional.orElseThrow(
                () -> new ResourceNotFoundException("Contact not found for specification..."));
        return this.mapper.toContactDto(contact);
    }

    @Override
    public ContactDto save(final ContactDto dto) {
        final var contact = this.repository.save(this.mapper.toContact(dto));
        log.debug("Contact created with ID [{}]", contact.getId());
        return this.mapper.toContactDto(contact);
    }

    @Override
    public void update(final ContactDto dto) {
        final var contactOriginal = this.repository.findById(dto.getId()).orElseThrow(()
                -> new ResourceNotFoundException(String.format("Contact with ID [%d] not found.", dto.getId())));
        final var contact = this.mapper.toContact(contactOriginal, dto);
        this.repository.save(contact);
        log.debug("Contact updated with ID [{}]", contact.getId());
    }

    @Override
    public void delete(final Long id) {
        final var contactOptional = this.repository.findById(id);
        final var contact = contactOptional.orElseThrow(()
                -> new ResourceNotFoundException(String.format("Contact with ID [%d] not found.", id)));
        this.repository.delete(contact);
        log.debug("Contact with ID [{}}] is deleted successfully... ", id);
    }
}
