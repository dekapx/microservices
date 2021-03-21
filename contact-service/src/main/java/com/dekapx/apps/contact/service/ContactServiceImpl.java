package com.dekapx.apps.contact.service;

import com.dekapx.apps.contact.model.ContactDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class ContactServiceImpl implements ContactService {
    @Override
    public void save(final ContactDto dto) {

    }
}
