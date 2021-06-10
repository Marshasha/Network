package ch.hevs.networkservice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.faces.event.ValueChangeEvent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.hibernate.query.NativeQuery;

import ch.hevs.businessobject.Computer;
import ch.hevs.businessobject.Device;
import ch.hevs.businessobject.MobilePhone;
import ch.hevs.businessobject.OperationalSystem;
import ch.hevs.businessobject.Tablet;
import ch.hevs.businessobject.User;
import ch.hevs.exception.NetworkException;

@Stateless
// @Stateful
// @RunAs("RegisteredManagers") ++ ( ou pour Stateless :  @RolesAllowed(value = { "userDevice", "managerPark" })  )
public class NetworkBean implements Network {
	
	@PersistenceContext(name = "networkPU", type=PersistenceContextType.TRANSACTION)
	private EntityManager em;

	/**
	 * User
	 */	
	
	public User getUserById(long id) {
		return (User) em.createQuery("SELECT u FROM User u WHERE u.id=:id")
				.setParameter("id", id).getSingleResult();
	}
	
	public User getUserByLoginName(String userLoginName) {
		
		return (User) em.createQuery("Select u From User u where u.loginName = :userLoginName").setParameter("userLoginName", userLoginName).getSingleResult();
	}
	
	public List<User> getUsersByDevice(Device device) {
		List <User> users = new ArrayList<User>();
				
		String className = device.getClass().toString();
		className = className.substring(className.lastIndexOf(".")+1);
		
		
		switch (className) {
		case "Computer":			
			Computer c = (Computer) getComputerById(((Computer) device).getiId());

			for (User user : c.getComputerOwners()) {			
				users.add(getUserById(user.getUserId()));
			}
			
			 break;
			 
		case "MobilePhone":		 
			MobilePhone mp = (MobilePhone) getMobilePhoneById(((MobilePhone) device).getiId());				
			users.add(((MobilePhone) device).getPhoneOwner());

				break;			

		case "Tablet":	
			Tablet t = (Tablet) getTabletById(((MobilePhone) device).getiId());			
			users.add(t.getTabletOwner());
			
			 break;			
		}

		if (users.isEmpty())
			users.add(new User("Pas", "d", "utilisateur"));

		return users;

	}

	public User addUser(String firstName, String lastName) {		
		
		
		User user = new User();
		user.setLoginName(makeLoginName(firstName, lastName));
		user.setFirstName(firstName);
		user.setLastName(lastName);
		
		em.persist(user);
				
		return user;
	}

	public String makeLoginName(String firstName, String lastName) {
		
		String loginName = firstName.substring(0, 3) + lastName.substring(0, 3);
		
		return loginName;
	}
	
	public List<User> getUsers() {		
		return (List<User>) em.createQuery("FROM User").getResultList();
	}

	public User getUser(long userId) {
		
		return (User) em.createQuery("FROM User u where u.userid=:userid").setParameter("userid", userId).getSingleResult();
	}

	
	/**
	 * OperationalSystem
	 */
	@Override
	public OperationalSystem getOSByName(String osName) {
		Query query = em.createQuery("FROM OperationalSystem a WHERE a.operationalSystemName=:osName");
		query.setParameter("osName", osName);

		return (OperationalSystem) query.getSingleResult();
	}	
	
	@Override
	public List<OperationalSystem> getListOS() {
		return (List<OperationalSystem>) em.createQuery("FROM OperationalSystem").getResultList();
	}

	public OperationalSystem getOsNameByDevice(Device device) {
		OperationalSystem os = new OperationalSystem();
		long id = device.getiId();
		
		String className = device.getClass().toString();
		className = className.substring(className.lastIndexOf(".")+1);
				
		switch (className) {
		case "Computer":			
			Computer c = (Computer) getComputerById(id);
			os = c.getOs();
			
			 break;
			 
		case "MobilePhone":		 
			MobilePhone mp = (MobilePhone) getMobilePhoneById(((MobilePhone) device).getiId());				
			os = mp.getOs();

				break;			

		case "Tablet":	
			Tablet t = (Tablet) getTabletById(((MobilePhone) device).getiId());			
			os = t.getOs();
			
			 break;			
		}


		return os;
	}

	/**
	 * Device
	 */	
	@Override
	public List<Computer> getComputers() {
		
		return (List<Computer>) em.createQuery("FROM Computer").getResultList();
	}

	
	@Override
	public List<MobilePhone> getMobilePhones() {

		return (List<MobilePhone>) em.createQuery("FROM MobilePhone").getResultList();
	}
	

	@Override
	public List<Tablet> getTablets() {

		return (List<Tablet>) em.createQuery("FROM Tablet").getResultList();
	}

	
	@Override
	public List<Computer> getComputersByOS(String os) {

		return (List<Computer>) em.createQuery("SELECT o.computers FROM OperationalSystem o where o.operationalSystemName=:os").setParameter("os", os).getResultList();
	}

	@Override
	public List<Tablet> getTabletsByOS(String os) {
				
		return (List<Tablet>) em.createQuery("SELECT o.tablets FROM OperationalSystem o where o.operationalSystemName=:os").setParameter("os", os).getResultList();
	}

	@Override
	public List<MobilePhone> getMobilePhonesByOS(String os) {

		return (List<MobilePhone>) em.createQuery("SELECT o.phones FROM OperationalSystem o where o.operationalSystemName=:os").setParameter("os", os).getResultList();
	}

	@Override
	public List<Device> getDevices() {
		List<Device> devices = new ArrayList<Device>();

		return devices;
		}

	@Override
	public List<Device> getDevicesByUser(User user) {
		List<Device> devices = new ArrayList<Device>();
		
		String loginName = user.getLoginName();
		
		user = (User) getUserByLoginName(loginName);
//		getDevices();
				
		if(!user.getComputers().isEmpty()) {
			for (Computer computer : user.getComputers()) {			
				devices.add(computer);
			}
		}
		else if (!user.getPhones().isEmpty()) {
			for (MobilePhone phone : user.getPhones()) {			
				devices.add(phone);
			}
		}
		else if (!user.getTablets().isEmpty()) {
			for (Tablet tablet : user.getTablets()) {			
				devices.add(tablet);
			}
		}
		
		return devices;
		
		
	}
	
	public Device getDeviceById(long id) {
		
		
		return (Device) em.createQuery("Select d From Device d where d.id=:deviceId").setParameter("deviceId", id ).getSingleResult();

	}

	public List<Computer> getAllComputersByUserLogin(String loginName) {
		
		return (List<Computer>) em.createQuery("SELECT u.computers FROM User u WHERE u.loginname=:loginname")
				.setParameter("loginname", loginName).getResultList();
	}
	
	public Computer getComputerById(long id) {
			
		return (Computer) em.createQuery("SELECT d FROM Computer d WHERE d.id=:id")
				.setParameter("id", id).getSingleResult();
	}
	 
	public MobilePhone getMobilePhoneById(long id) {
					
		return (MobilePhone) em.createQuery("SELECT d FROM MobilePhone d WHERE d.id=:id")
				.setParameter("id", id).getSingleResult();
	}
	
	public Tablet getTabletById(long id) {
		
		return (Tablet) em.createQuery("SELECT d FROM Tablet d WHERE d.id=:id")
				.setParameter("id", id).getSingleResult();
	}

	public void deleteEntity(Object object) {
				
		object = em.merge(object);
		
		int updating = -1 ;
		String queryString = "";
		String className = object.getClass().toString();
		className = className.substring(className.lastIndexOf(".")+1);
		
		switch (className) {
		case "User":
			queryString = "DELETE FROM User e WHERE e=:ref";
			 break;
			 
		case "OperationalSystem":			
			queryString = "DELETE FROM OperationalSystem e WHERE e=:ref";
			 break;	

		case "Device":
			
			queryString = "DELETE FROM Device e WHERE e=:ref";

			 break;

		case "Computer":			
			queryString = "DELETE FROM Computer e WHERE e=:ref";
			 break;
			 
		case "MobilePhone":		 
			queryString = "DELETE FROM MobilePhone e WHERE e=:ref";
			 break;			

		case "Tablet":		 
			queryString = "DELETE FROM Tablet e WHERE e=:ref";
			 break;			
		}

		updating = em.createQuery(queryString).setParameter("ref", object ).executeUpdate();
		
	}
	
	public void addDevice(String deviceName, String deviceClass, String osName) {
		
		OperationalSystem os = getOSByName(osName);

		switch (deviceClass) {
		case "Computer":			
			Computer computer = new Computer (deviceName);
			computer.setOs(os);
			em.persist(computer);
			 break;
			 
		case "MobilePhone":		 
			MobilePhone mp = new MobilePhone (deviceName);
			mp.setOs(os);
			em.persist(mp);			 
			break;			

		case "Tablet":		 
			Tablet t = new Tablet (deviceName);
			t.setOs(os);
			em.persist(t);			 
			break;			
		}
				
	}
	
	/**
	 * Populate
	 */
	public void populate() {
					
		OperationalSystem os1 = new OperationalSystem("Ouindose");
		OperationalSystem os = new OperationalSystem("Linoux");
		
		 em.persist(os1);
		 em.persist(os);

			User u1 = new User("PlaMic", "Platini", "Michel");

			Computer c1 = new Computer("Asus");
			c1.setOs(os);
			em.persist(c1);
			u1.addComputer(c1);
			em.persist(u1);
			
			User u2 = new User("ParTon", "Parker", "Tony");
			Computer c2 = new Computer("Dell");			 
			c2.setOs(os1);
			em.persist(c2);
			u2.addComputer(c2);			
			em.persist(u2);
			
			Computer comp1 = new Computer ("Azus"); 
			Computer comp2 = new Computer ("Lenouvo"); 
			Computer comp3 = new Computer ("Bell"); 
			Computer comp4 = new Computer ("Appeul");
			 
			 comp1.setOs(os1);
			 comp2.setOs(os);
			 comp3.setOs(os1);
			 comp4.setOs(os);

			 em.persist(comp1);
			 em.persist(comp2);
			 em.persist(comp3);
			 em.persist(comp4);

			 
			 MobilePhone phone1 = new MobilePhone ("Efon");
			 phone1.setOs(os);
			 em.persist(phone1);
			 
			 Tablet tablet1 = new Tablet ("Samsong");
			 tablet1.setOs(os1);
			 em.persist(tablet1);
	
			
	}

	
}
