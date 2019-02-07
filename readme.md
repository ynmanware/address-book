# Address Book

Following features are developed as part of this assignment 

- Address book should hold name and phone numbers of contact entries
- Users should be able to add a new contact entry to an existing address book
- Users should be able to remove an existing contact entry from an existing address book
- Users should be able to retrieve all contacts in an address book
- Users should be able to retrieve a unique set of all contacts across multiple address books
##### Assumptions
- There are already two existing address books initialised in memory
- REST endpoints for maintaining address books are not required (e.g AddressBook CRUD operations)
- Design & implement RESTful API services to meet the above requirements; a working user interface is not required. Data should only be persisted in memory. Deployment is NOT required. Please provide instructions on how to run the application. You may make reasonable assumptions if acceptance criteria are not clear.
 
 
##### Note that it is not a production qualiity code but just a demonstration of a few aspects of
 - Spring boot
 - REST
 - Validation
 - Unit Testing 
 
# How to use to start and use 

### Build
```mvn install```

### Test
```mvn test```

### Start
```mvn springboot:run```


## REST ENDPOINTS
###### Get contacts for a given address book
*GET* ```http://localhost:4000/api/v1/address-book/address-book-melbourne/contacts```

*GET* ```http://localhost:4000/api/v1/address-book/address-book-sydney/contacts```

###### Add Contact
*POST* ```http://localhost:4000/api/v1/address-book/melbourne/contacts```

###### Payload: 
```
{
	"firstName": "B",
	"lastName": "A",
	"phoneNumbers": ["988998897", "989987677"]
}
```

###### Delete contact
*DELETE* ```http://localhost:4000/api/v1/address-book/address-book-sydney/contacts/<uuid>```

###### Get Unique Contacts
*GET* ```http://localhost:4000/api/v1/contacts?unique=true```

###### Get All Contacts
*GET* ```http://localhost:4000/api/v1/contacts?unique=false```