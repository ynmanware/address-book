package com.assignment.addressbook.service;

import com.assignment.addressbook.model.Contact;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * I have added a few test cases for demonstration
 * more test cases are possible
 */

public class ContactServiceTest {

    private static final String TEST_ADDRESS_BOOK_MEL = "AddressBookMelbourne";
    private static final String TEST_ADDRESS_BOOK_SYD = "AddressBookSydney";

    private ContactService contactService;

    @Before
    public void init() {
        contactService = new ContactService();
    }

    @Test
    public void testAddContact() {
        Contact addedContact = contactService.addContact(TEST_ADDRESS_BOOK_MEL, getNewTestContact());
        Contact contact = getNewTestContact();
        Assert.assertTrue(contact.equals(addedContact));
    }

    @Test
    public void testRemoveContact() {
        Contact addedContact = contactService.addContact(TEST_ADDRESS_BOOK_MEL, getNewTestContact());
        contactService.removeContact(TEST_ADDRESS_BOOK_MEL, addedContact.getId());
        try {
            contactService.removeContact(TEST_ADDRESS_BOOK_MEL, addedContact.getId());
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage().equals("The given contact does not exist!"));
        }
    }

    @Test
    public void testGetContactsFromGivenAddressBook() {
        Contact addedContact1 = contactService.addContact(TEST_ADDRESS_BOOK_MEL, getNewTestContact());
        Contact addedContact2 = contactService.addContact(TEST_ADDRESS_BOOK_MEL, getNewTestContact());
        Contact contact3 = getNewTestContact();
        contact3.setFirstName("Marc");
        Contact addedContact3 = contactService.addContact(TEST_ADDRESS_BOOK_MEL, contact3);

        List<Contact> retrievedContacts = contactService.retrieveContacts(TEST_ADDRESS_BOOK_MEL);
        List<Contact> addedContacts = Arrays.asList(addedContact1, addedContact2, addedContact3);

        Assert.assertTrue(addedContacts.containsAll(retrievedContacts));
        Assert.assertTrue(retrievedContacts.containsAll(addedContacts));
    }

    //
    @Test
    public void validateAddContactThrowsExceptionWhenAddressBookDoesNotExist() {
        contactService.addContact(TEST_ADDRESS_BOOK_MEL, getNewTestContact());
        try {
            contactService.retrieveContacts(TEST_ADDRESS_BOOK_SYD);
            Assert.fail("Expected an exception when the given address book does not exist.");
        } catch (Exception e) {
            Assert.assertEquals("AddressBook with the given Id does not exist", e.getMessage());
        }
    }


    @Test
    public void testAllUniqueContacts() {

        List<Contact> retrievedContactsBeforeAddingDuplicates = contactService.retrieveAllUniqueContacts(true);

        //add contact1
        contactService.addContact(TEST_ADDRESS_BOOK_MEL, getNewTestContact());

        //same as contact1
        contactService.addContact(TEST_ADDRESS_BOOK_MEL, getNewTestContact());

        //contact2
        Contact contact3 = getNewTestContact();
        contact3.setFirstName("Marc");

        contactService.addContact(TEST_ADDRESS_BOOK_SYD, contact3);

        //same as Contact1
        contactService.addContact(TEST_ADDRESS_BOOK_SYD, getNewTestContact());

        //At this point we added 4 contacts but the below returned list should contain addition of 2 new records
        List<Contact> retrievedContactsAfterAddingDuplicates = contactService.retrieveAllUniqueContacts(true);

        Assert.assertTrue(2 == retrievedContactsAfterAddingDuplicates.size() - retrievedContactsBeforeAddingDuplicates.size());
    }

    @Test
    public void testAllContacts() {

        List<Contact> retrievedContactsBeforeAddingDuplicates = contactService.retrieveAllUniqueContacts(false);

        //add contact1
        contactService.addContact(TEST_ADDRESS_BOOK_MEL, getNewTestContact());

        //same as contact1
        contactService.addContact(TEST_ADDRESS_BOOK_MEL, getNewTestContact());

        //contact2
        Contact contact3 = getNewTestContact();
        contact3.setFirstName("Marc");

        contactService.addContact(TEST_ADDRESS_BOOK_SYD, contact3);

        //same as Contact1
        contactService.addContact(TEST_ADDRESS_BOOK_SYD, getNewTestContact());

        //At this point we added 4 contacts but the below returned list should contain addition of 2 new records
        List<Contact> retrievedContactsAfterAddingDuplicates = contactService.retrieveAllUniqueContacts(false);

        Assert.assertTrue(4 == retrievedContactsAfterAddingDuplicates.size() - retrievedContactsBeforeAddingDuplicates.size());
    }

    private Contact getNewTestContact() {
        return new Contact("Tom", "Cox", Arrays.asList("0421435534", "0432543123"));
    }

}
