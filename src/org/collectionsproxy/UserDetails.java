package org.collectionsproxy;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;


@Entity 
/**@Entity (name="USER_DATA") creates a table with name USER_DATA instead of 
class-name UserData  **/
@Table (name="USER_DETAILS")
public class UserDetails {	
	@Id
	@GeneratedValue
	private int userId;
	private String userName;
	
	
	/* The ElementCollection annotation is configured using Fetch type EAGER;
	 * This is called eager initialization; 
	 * When LAZY type is used it is called lazy initialization; where the data cannot be returned to user1 object;
	 */	
	@ElementCollection(fetch = FetchType.EAGER)
	@JoinTable (name = "USER_ADDRESS", joinColumns = @JoinColumn(name = "USER_ID"))
	private Collection<Address> listOfAddresses = new ArrayList<Address>();	
	
	public Collection<Address> getListOfAddresses() {
		return listOfAddresses;
	}
	public void setListOfAddresses(Collection<Address> listOfAddresses) {
		this.listOfAddresses = listOfAddresses;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
