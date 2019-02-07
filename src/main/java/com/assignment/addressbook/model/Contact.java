package com.assignment.addressbook.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Objects;

public class Contact {

    private String id;
    @NotNull(message = "Please provide first Name")
    @NotEmpty(message = "Please provide first Name")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9-]*$", message = "first name must be alphanumeric and start with character")
    private String firstName;

    @NotNull(message = "Please provide last Name")
    @NotEmpty(message = "Please provide last Name")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9-]*$", message = "last name must be alphanumeric and start with character")
    private String lastName;

    //TODO: more validations using custom validator
    @NotNull(message = "Please provide phone numbers")
    @NotEmpty(message = "Please provide phone numbers")
    List<String> phoneNumbers;

    public Contact(String firstName, String lastName, List<String> phoneNumbers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumbers = phoneNumbers;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(firstName, contact.firstName) &&
                Objects.equals(lastName, contact.lastName) &&
                phoneNumbers.containsAll(contact.getPhoneNumbers())
                && contact.getPhoneNumbers().containsAll(phoneNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phoneNumbers);
    }
}

