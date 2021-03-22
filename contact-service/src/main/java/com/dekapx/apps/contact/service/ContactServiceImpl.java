package com.dekapx.apps.contact.service;

import com.dekapx.apps.contact.mapper.ContactMapper;
import com.dekapx.apps.contact.model.ContactDto;
import com.dekapx.apps.contact.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private ContactMapper mapper;
    private ContactRepository repository;

    @Override
    public void save(final ContactDto dto) {

    }
}
