package com.dekapx.apps.contact.service;

import com.dekapx.apps.contact.domain.Contact;
import com.dekapx.apps.contact.mapper.ContactMapper;
import com.dekapx.apps.contact.model.ContactDto;
import com.dekapx.apps.contact.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void save(final ContactDto dto) {
        final Contact entity = this.mapper.toContact(dto);
        this.repository.save(entity);
    }
}
