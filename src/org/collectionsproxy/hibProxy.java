package org.collectionsproxy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class hibProxy {

	public static void main(String[] args) {
		
		UserDetails user1 = new UserDetails();
		
		//user1.setUserId(1); GeneratedValue annotation generates the integer values(starting from 1) defined at UserData
		user1.setUserName("User One");
		
		Address addr1= new Address();		
		addr1.setCity("city");
		addr1.setState("state");
		addr1.setPin("50266");
		addr1.setStreet("street");
		
		
		Address addr2= new Address();
		
		addr2.setCity("city2");
		addr2.setState("state2");
		addr2.setPin("50201");
		addr2.setStreet("street2");
	
		
		user1.getListOfAddresses().add(addr1);
		user1.getListOfAddresses().add(addr2);
		
		SessionFactory sessionFactory= new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user1);
		session.getTransaction().commit();
		session.close();
		
		session = sessionFactory.openSession();
		user1 = (UserDetails) session.get(UserDetails.class,1);
		session.close();
		/* When the session is closed hibernate cannot retrieve the listOfAddresses
		 * But when we configure the ElementCollection annotation on the listOfAddresses object;
		 * Hibernate can then retrieve the data 
		 */
		System.out.println(user1.getListOfAddresses().size());

	}

}
