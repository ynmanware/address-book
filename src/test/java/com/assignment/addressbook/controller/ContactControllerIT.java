package com.assignment.addressbook.controller;

import com.assignment.addressbook.AddressBookApplication;
import com.assignment.addressbook.model.Contact;
import com.assignment.addressbook.service.ContactService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;


/**
 * I have added a few test cases for demonstration
 * More test cases could be added to make the code more robust
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AddressBookApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContactControllerIT {

    private static final String TEST_ADDRESS_BOOK = "address-book1";
    private static final String CONTACTS_END_POINT = "/api/v1/address-book/address-book1/contacts";


    @LocalServerPort
    private int port;

    @Autowired
    private ContactService contactService;

    TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testAddContact() {
        Contact contact = new Contact("first-name", "last-name", Arrays.asList("0412 123 345", "0412 123 346"));
        HttpEntity<Contact> entity = new HttpEntity<>(contact);

        ResponseEntity<Contact> response = restTemplate.exchange(
                createURLWithPort(CONTACTS_END_POINT),
                HttpMethod.POST, entity, Contact.class);

        Assert.assertTrue(response.getStatusCode().value() == 200);

        contactService.removeContact(TEST_ADDRESS_BOOK, response.getBody().getId());
    }

    @Test
    public void testRemoveContact() {
        Contact contact = new Contact("first-name", "last-name", Arrays.asList("0412 123 345", "0412 123 346"));
        Contact addedContact = contactService.addContact(TEST_ADDRESS_BOOK, contact);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort(TEST_ADDRESS_BOOK + addedContact.getId()),
                HttpMethod.DELETE, new HttpEntity<String>(null, null), String.class);

        Assert.assertEquals(response.getStatusCode().value(), 200);

        Assert.assertFalse(contactService.retrieveContacts(TEST_ADDRESS_BOOK).stream()
                .anyMatch(contact1 -> contact1.getId().equals(addedContact.getId())));
    }

    @Test
    public void testRetrieveAllContactsFromAddressBook() {
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/v1/address-book/address-book-Id1/contacts"),
                HttpMethod.GET, new HttpEntity<String>(null, null), String.class);
        Assert.assertEquals(response.getStatusCode().value(), 200);
    }

    @Test
    public void testRetrieveAllUniqueContacts() {
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/v1/contacts"),
                HttpMethod.GET, new HttpEntity<String>(null, null), String.class);
        Assert.assertEquals(response.getStatusCode().value(), 200);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
