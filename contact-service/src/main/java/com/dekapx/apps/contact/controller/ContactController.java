package com.dekapx.apps.contact.controller;

import com.dekapx.apps.contact.model.ContactModel;
import com.dekapx.apps.contact.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @Operation(summary = "Find Contact by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Contact",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ContactModel.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Contact with ID [x] not found.", content = @Content)
    })
    @GetMapping(value = "/contact/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContactModel> findContactById(@PathVariable Long id) {
        log.debug("Find Contact for ID [{}]", id);
        final var contact = this.contactService.findById(id);
        return new ResponseEntity<ContactModel>(contact, HttpStatus.OK);
    }

    @Operation(summary = "Find Contacts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Contacts",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ContactModel.class))
                    }),
            @ApiResponse(responseCode = "204", description = "No Content.", content = @Content)
    })
    @GetMapping(value = "/contacts",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ContactModel>> findAll() {
        log.debug("Find all contacts");
        final var contacts = this.contactService.findAll();
        if (contacts.isEmpty()) {
            return new ResponseEntity<>(contacts, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ContactModel>>(contacts, HttpStatus.OK);
    }

    @Operation(summary = "Create New contact", tags = {"contact"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New Contact created",
                    content = @Content(schema = @Schema(implementation = ContactModel.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Contact already exists")})
    @PostMapping(value = "/contact/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContactModel> create(@Valid @RequestBody ContactModel contactDto) {
        log.debug("Create new contact...");
        return new ResponseEntity<ContactModel>(this.contactService.save(contactDto), HttpStatus.OK);
    }
}
