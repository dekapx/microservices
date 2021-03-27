package com.dekapx.apps.contact.controller;

import com.dekapx.apps.contact.model.ContactDto;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @Operation(summary = "Get a Contact by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the User",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ContactDto.class))}),
            @ApiResponse(responseCode = "404", description = "Contact with ID [2] not found.", content = @Content)})
    @GetMapping(value = "/contact/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContactDto> getContact(@PathVariable Long id) {
        log.debug("Find Contact for ID [{}]", id);
        final var userDto = this.contactService.findById(id);
        return new ResponseEntity<ContactDto>(userDto, HttpStatus.OK);
    }
}
