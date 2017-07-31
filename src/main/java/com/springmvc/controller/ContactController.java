package com.springmvc.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.springmvc.model.Contact;
import com.springmvc.service.ContactService;
import com.springmvc.service.ContactServiceImpl;

@RestController
public class ContactController {
	
    //@Autowired
    //ContactService userService;  //Service which will do all data retrieval/manipulation work
  
     
    //-------------------Retrieve All Contacts--------------------------------------------------------
      
    @RequestMapping(value = "getContacts/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Contact>> listAllContacts() { 	
        ContactService csImpl = new ContactServiceImpl();
        List<Contact> contacts = csImpl.findAllContacts();
        
        if(contacts.isEmpty()){
            return new ResponseEntity<List<Contact>>(HttpStatus.NO_CONTENT);//return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Contact>>(contacts, HttpStatus.OK);
    }
  
     
    //-------------------Retrieve Single Contact--------------------------------------------------------
      
    @RequestMapping(value = "searchContact/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contact> getContact(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        
        ContactService csImpl = new ContactServiceImpl();
        Contact contact = csImpl.findById(id);
        if (contact == null) {
            System.out.println("Contact with id " + id + " not found");
            return new ResponseEntity<Contact>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Contact>(contact, HttpStatus.OK);
    }
  
      
      
    //-------------------Save a Contact--------------------------------------------------------
      
    @RequestMapping(value = "saveContact/", method = RequestMethod.POST)
    public ResponseEntity<Void> saveContact(@RequestBody Contact contact, UriComponentsBuilder ucBuilder) {        
        ContactService csImpl = new ContactServiceImpl();
        
        System.out.println("Save contact " + contact.getEmail());
        if (csImpl.isContactExist(contact)) {
            System.out.println("A contact with email " + contact.getEmail() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
  
        csImpl.saveContact(contact);
  
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(contact.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
  
     
      
    //------------------- Update a Contact --------------------------------------------------------
      
    @RequestMapping(value = "updateContact/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Contact> updateContact(@PathVariable("id") long id, @RequestBody Contact contact) {
    	ContactService csImpl = new ContactServiceImpl();
        System.out.println("Updating contact " + id);
          
        Contact currentContact = csImpl.findById(id);
          
        if (currentContact==null) {
            System.out.println("Contact with id " + id + " not found");
            return new ResponseEntity<Contact>(HttpStatus.NOT_FOUND);
        }
  
        currentContact.setName(contact.getName());

        currentContact.setEmail(contact.getEmail());
          
        csImpl.updateContact(currentContact);
        return new ResponseEntity<Contact>(currentContact, HttpStatus.OK);
    }
  
     
     
    //------------------- Delete a Contact --------------------------------------------------------
      
    @RequestMapping(value = "deleteContact/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Contact> deleteContact(@PathVariable("id") long id) {
    	ContactService csImpl = new ContactServiceImpl();
        System.out.println("Fetching & Deleting Contact with id " + id);
  
        Contact contact = csImpl.findById(id);
        if (contact == null) {
            System.out.println("Unable to delete. Contact with id " + id + " not found");
            return new ResponseEntity<Contact>(HttpStatus.NOT_FOUND);
        }
  
        csImpl.deleteContactById(id);
        return new ResponseEntity<Contact>(HttpStatus.NO_CONTENT);
    }
  
      
    //------------------- Delete All Contacts --------------------------------------------------------
      
    @RequestMapping(value = "delateAllContacts/", method = RequestMethod.DELETE)
    public ResponseEntity<Contact> deleteAllContacts() {
    	ContactService csImpl = new ContactServiceImpl();
        System.out.println("Deleting All Contacts");
  
        csImpl.deleteAllContacts();
        return new ResponseEntity<Contact>(HttpStatus.NO_CONTENT);
    }
  
}
