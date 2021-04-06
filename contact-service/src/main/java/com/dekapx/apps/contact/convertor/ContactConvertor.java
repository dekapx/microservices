package com.dekapx.apps.contact.convertor;

import com.dekapx.apps.contact.domain.Contact;
import com.dekapx.apps.contact.model.ContactDto;
import com.dekapx.apps.core.convertor.Convertor;
import org.springframework.stereotype.Component;

@Component("contactConvertor")
public class ContactConvertor implements Convertor<Contact, ContactDto> {
    @Override
    public Contact toEntity(final ContactDto dto) {
        final Contact contact = new Contact();
        contact.setFirstName(dto.getFirstName());
        contact.setLastName(dto.getLastName());
        contact.setEmail(dto.getEmail());
        contact.setPhone(dto.getPhone());
        return contact;
    }

    @Override
    public ContactDto toDto(final Contact entity) {
        return ContactDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .build();
    }

    @Override
    public void copyProperties(final Contact entity, final ContactDto dto) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
    }
}
