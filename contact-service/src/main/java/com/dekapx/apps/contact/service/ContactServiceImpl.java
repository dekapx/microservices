package com.dekapx.apps.contact.service;

import com.dekapx.apps.contact.domain.Contact;
import com.dekapx.apps.contact.mapper.ContactMapper;
import com.dekapx.apps.contact.model.ContactDto;
import com.dekapx.apps.contact.repository.ContactRepository;
import com.dekapx.apps.core.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    public void save(final ContactDto dto) {
        final Contact contact = this.repository.save(this.mapper.toContact(dto));
        log.debug("Contact created with ID [{}]", contact.getId());
    }

    @Override
    public ContactDto findById(final Long id) {
        final Optional<Contact> contactOptional = this.repository.findById(id);
        final Contact contact = contactOptional.orElseThrow(
                () -> new ResourceNotFoundException(String.format("Contact with ID [%d] not found.", id)));
        return this.mapper.toContactDto(contact);
    }
}
