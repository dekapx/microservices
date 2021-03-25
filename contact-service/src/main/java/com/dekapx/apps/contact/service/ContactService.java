package com.dekapx.apps.contact.service;

import com.dekapx.apps.contact.model.ContactDto;

public interface ContactService {
    void save(ContactDto dto);
    ContactDto findById(Long id);
}
