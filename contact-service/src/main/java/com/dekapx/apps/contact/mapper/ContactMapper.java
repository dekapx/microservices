package com.dekapx.apps.contact.mapper;

import com.dekapx.apps.contact.domain.Contact;
import com.dekapx.apps.contact.model.ContactDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    ContactDto toContactDto(Contact contact);
    Contact toContact(ContactDto dto);

    default Contact toContact(Contact sourceEntity, ContactDto dto) {
        Contact contact = toContact(dto);
        contact.setCreatedBy(sourceEntity.getCreatedBy());
        contact.setCreatedDate(sourceEntity.getCreatedDate());
        return contact;
    }
}
