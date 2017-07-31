package com.springmvc.service;


import java.util.ArrayList;

//import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

import com.springmvc.model.Contact;
import com.springmvc.model.ContactData;

import java.sql.*;

@Service("contactService")
public class ContactServiceImpl implements ContactService{
    
    private final AtomicLong counter = new AtomicLong();
     
    //private List<Contact> contacts;
     
    //public ContactServiceImpl() {
    //    contacts= populateDummyContacts();
    //}
 
    public List<Contact> findAllContacts() {
    	List<Contact> contacts = new ArrayList<Contact>();
    	ContactData con = new ContactData();
    	ResultSet rs = con.getAllContacts();
    	try {
			while(rs.next()) 
				contacts.add(new Contact(Long.parseLong(rs.getString(1)), rs.getString(2), rs.getString(3)));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	con.closeConnection();
    	return contacts;
    }
     
    public Contact findById(long id) {
    	Contact contact = new Contact();
   	 	ContactData con = new ContactData();
   	 	ResultSet rs = con.getContactById(id);
	   	try {
				((List<Contact>) contact).add(new Contact(Long.parseLong(rs.getString(1)), rs.getString(2), rs.getString(3)));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   	con.closeConnection();
	   	return contact;
    }
     
    public Contact findByName(String name) {
    	Contact contact = new Contact();
   	 	ContactData con = new ContactData();
   	 	ResultSet rs = con.getContactByName(name);
	   	try {
				((List<Contact>) contact).add(new Contact(Long.parseLong(rs.getString(1)), rs.getString(2), rs.getString(3)));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   	con.closeConnection();
	   	return contact;
    }
     
    public int saveContact(Contact contact) {
   	 	ContactData con = new ContactData();
   	 	int rs = con.saveContact(contact);
        return rs;
    }
 
    public int updateContact(Contact contact) {
  	 	ContactData con = new ContactData();
   	 	int rs = con.updateContact(contact);
        return rs;
    }
 
    public int deleteContactById(long id) {  
  	 	ContactData con = new ContactData();
   	 	int rs = con.deleteContact(id);
        return rs;
    }
 
    public boolean isContactExist(String name) {
    	ContactData con = new ContactData();
        return con.getContactByName(name)!=null;
    }
     
    public int deleteAllContacts(){
  	 	ContactData con = new ContactData();
   	 	int rs = con.deleteAllContact();
        return rs;
    }
    public boolean isContactExist(Contact contact) {
   	 	ContactData con = new ContactData();
   	 	boolean rs = false;
		try {
			rs = con.isContactExist(contact.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	 	return rs;
    }
 
    private List<Contact> populateDummyContacts(){
        List<Contact> contacts = new ArrayList<Contact>();
        contacts.add(new Contact(counter.incrementAndGet(),"Sam", "sam@abc.com"));
        contacts.add(new Contact(counter.incrementAndGet(),"Tomy", "tomy@abc.com"));
        contacts.add(new Contact(counter.incrementAndGet(),"Kelly", "kelly@abc.com"));
        return contacts;
    }
 
}


