package com.assignment.addressbook.controller;

import java.util.List;

import com.assignment.addressbook.model.Contact;
import com.assignment.addressbook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController

public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/api/v1/address-book/{addressBookId}/contacts")
    public Contact addContactToAddressBook(
            @PathVariable String addressBookId, @Valid @RequestBody Contact newContact) {

        return contactService.addContact(addressBookId, newContact);
    }

    @DeleteMapping("/api/v1/address-book/{addressBookId}/contacts/{contactId}")
    public Contact removeContactFromAddressBook(@PathVariable String addressBookId,
                                                @PathVariable String contactId) {
        return contactService.removeContact(addressBookId, contactId);
    }

    @GetMapping("/api/v1/address-book/{addressBookId}/contacts")
    public List<Contact> retrieveContactsFromAddressBook(@PathVariable String addressBookId) {
        return contactService.retrieveContacts(addressBookId);
    }

    @GetMapping("/api/v1/contacts")
    public List<Contact> retrieveUniqueContactsFromAllAddressBooks(@RequestParam(value = "unique", defaultValue = "false") Boolean unique) {
        return contactService.retrieveAllUniqueContacts(unique);
    }

}
