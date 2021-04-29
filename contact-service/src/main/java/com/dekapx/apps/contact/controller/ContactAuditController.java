package com.dekapx.apps.contact.controller;

import com.dekapx.apps.contact.domain.Contact;
import com.dekapx.apps.core.audit.AuditService;
import lombok.extern.slf4j.Slf4j;
import org.javers.core.Changes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/contact")
public class ContactAuditController {
    @Autowired
    @Qualifier("contactAuditService")
    private AuditService<Contact> auditService;

    @GetMapping(value = "/audit/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Changes> findContactById(@PathVariable Long id) {
        log.info("Find Contact for ID [{}]", id);
        final Changes changes = this.auditService.findChanges(id);
        return new ResponseEntity<>(changes, HttpStatus.OK);
    }
}
