package com.dekapx.apps.contact.convertor;

import com.dekapx.apps.contact.domain.Contact;
import com.dekapx.apps.contact.model.ContactDto;
import com.dekapx.apps.core.convertor.Convertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContactConvertor implements Convertor<Contact, ContactDto> {
    private final ContactMapper mapper;

    @Autowired
    public ContactConvertor(final ContactMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Contact toEntity(final ContactDto dto) {
        return this.mapper.toContact(dto);
    }

    @Override
    public ContactDto toDto(final Contact entity) {
        return this.mapper.toContactDto(entity);
    }
}
