package com.dekapx.apps.contact.convertor;

import com.dekapx.apps.contact.domain.Contact;
import com.dekapx.apps.contact.model.ContactDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    ContactDto toContactDto(Contact contact);
    Contact toContact(ContactDto dto);
}
