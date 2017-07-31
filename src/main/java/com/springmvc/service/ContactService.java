package com.springmvc.service;


//import java.sql.ResultSet;
import java.util.List;

import com.springmvc.model.Contact;

//import maven.src.Contact;
 
public interface ContactService {
    
    Contact findById(long id);
     
    Contact findByName(String name);
     
    int saveContact(Contact contact);
     
    int updateContact(Contact contact);
     
    int deleteContactById(long id);
 
    List<Contact> findAllContacts(); 
     
    int deleteAllContacts();
     
    public boolean isContactExist(Contact contact);
     
}