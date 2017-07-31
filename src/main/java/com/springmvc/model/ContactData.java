package com.springmvc.model;

import java.sql.*;  
public class ContactData {

		
		private Connection con;
		private Statement stmt;
		
		public ContactData() {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			try {
				con=DriverManager.getConnection(  
						"jdbc:mysql://localhost:3306/myemail","root","tiger");
				stmt=con.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
	    public ResultSet getAllContacts() {
			ResultSet rs = null;
			try {
				rs = stmt.executeQuery("select * from contact;");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
	        return rs;
	    }

	    public ResultSet getContactById(long id) {
			ResultSet rs = null;
			try {
				rs = stmt.executeQuery("select * from contact where id='" + id + "';");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
	        return rs;
	    }

	    public ResultSet getContactByName(String name) {
			ResultSet rs = null;
			try {
				rs = stmt.executeQuery("select * from contact where name='" + name + "';");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
	        return rs;
	    }
	    public ResultSet getContactByEmail(String email) {
			ResultSet rs = null;
			try {
				rs = stmt.executeQuery("select * from contact where email='" + email + "';");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
	        return rs;
	    }
	    
	    public int saveContact(Contact contact) {
			int rs = 0;
			try {
				rs = stmt.executeUpdate("insert into contact (id, name, email) values (" + contact.getId() + ",'" + contact.getName() + "','" + contact.getEmail() + "');");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
	        return rs;
	    }		 
  
	    public int updateContact(Contact contact) {
			int rs = 0;
			try {
				rs = stmt.executeUpdate("update contact set name='" + contact.getName() + "', email='" + contact.getEmail() + "' where id=" + contact.getId() + ";");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
	        return rs;
	    }
	    
	    public int deleteContact(long id) {
			int rs = 0;
			try {
				rs = stmt.executeUpdate("delete from contact where id=" + id + ";");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
	        return rs;
	    }
	    
	    public int deleteAllContact() {
			int rs = 0;
			try {
				rs = stmt.executeUpdate("delete from contact;");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
	        return rs;
	    }
	    
	    public boolean isContactExist(long id) throws SQLException {
	    	ResultSet rs = null;
			try {
				rs = stmt.executeQuery("select * from contact where id=" + id + ";");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
			return rs.first();
	    }	    
	    
	    public void closeConnection() {
	    	try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

}
