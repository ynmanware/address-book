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
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AddressBookApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContactControllerValidation {
    private static final String CONTACTS_END_POINT = "/api/v1/address-book/address-book1/contacts";
    @LocalServerPort
    private int port;

    @Autowired
    private ContactService contactService;

    TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void validateAddContactMustReturn400OnInvalidFirstName() {
        Contact contact = new Contact("123", "last-name", Arrays.asList("0412 123 345", "0412 123 346"));
        HttpEntity<Contact> entity = new HttpEntity<>(contact);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort(CONTACTS_END_POINT),
                HttpMethod.POST, entity, String.class);

        Assert.assertTrue(response.getStatusCode().value() == 400);
    }

    @Test
    public void validateAddContactMustReturn400OnInvalidLastName() {
        Contact contact = new Contact("Yogesh", "$234$", Arrays.asList("0412 123 345", "0412 123 346"));
        HttpEntity<Contact> entity = new HttpEntity<>(contact);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort(CONTACTS_END_POINT),
                HttpMethod.POST, entity, String.class);

        Assert.assertTrue(response.getStatusCode().value() == 400);
    }

    @Test
    public void validateAddContactMustReturn400OnInvalidPhoneNumbners() {
        Contact contact = new Contact("Yogesh", "Manware", Arrays.asList());
        HttpEntity<Contact> entity = new HttpEntity<>(contact);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort(CONTACTS_END_POINT),
                HttpMethod.POST, entity, String.class);

        Assert.assertTrue(response.getStatusCode().value() == 400);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
