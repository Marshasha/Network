package ch.hevs.networkservice;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import ch.hevs.businessobject.Computer;
import ch.hevs.businessobject.Device;
import ch.hevs.businessobject.MobilePhone;
import ch.hevs.businessobject.OperationalSystem;
import ch.hevs.businessobject.Tablet;
import ch.hevs.businessobject.User;

@Stateless
public class NetworkBean implements Network {
	
	@PersistenceContext(name = "networkPU", type=PersistenceContextType.TRANSACTION)
	private EntityManager em;

/*	public List<Computer> getComputerByUser(String userLoginName) {

		return (List<Computer>) em.createQuery("FROM Computer c WHERE c.owner.userLoginName=:userLoginName").setParameter("userLoginName", userLoginName).getResultList();
	}
	
	public  List<MobilePhone> getMobilePhoneByUser(String userLoginName) {

		return (List<MobilePhone>) em.createQuery("FROM MobilePhone mp WHERE mp.owner.userLoginName=:userLoginName").setParameter("userLoginName", userLoginName).getResultList();
	}
	
	public  List<Tablet> getTabletByUser(String userLoginName) {

		return (List<Tablet>) em.createQuery("FROM Tablet t WHERE t.owner.userLoginName=:userLoginName").setParameter("userLoginName", userLoginName).getResultList();
	}
	
	public List<Computer> getUserByComputer(String computerName) {

		return (List<Computer>) em.createQuery("FROM User u WHERE u.name=:computerName").setParameter("computerName", computerName).getResultList();
	}
	
	public  List<MobilePhone> getMobilePhoneByUser(String userLoginName) {

		return (List<MobilePhone>) em.createQuery("FROM MobilePhone mp WHERE mp.owner.userLoginName=:userLoginName").setParameter("userLoginName", userLoginName).getResultList();
	}
	
	public  List<Tablet> getTabletByUser(String userLoginName) {

		return (List<Tablet>) em.createQuery("FROM Tablet t WHERE t.owner.userLoginName=:userLoginName").setParameter("userLoginName", userLoginName).getResultList();
	}
*/
	public List<Computer> getAllComputersByUserLogin(String loginName) {
		
		return (List<Computer>) em.createQuery("SELECT u.computers FROM USER u WHERE u.loginname=:loginname")
				.setParameter("loginname", loginName).getResultList();
	}

	public void addComputer(Computer c, User u) {

		em.persist(c);
		em.persist(u);
		u.addComputer(c);
		c.addOwner(u);;
		
	}
	
	public List<User> getUsers() {		
		return (List<User>) em.createQuery("FROM User").getResultList();
	}

	public User getUser(long userId) {
		
		return (User) em.createQuery("FROM User u where u.userid=:userid").setParameter("userid", userId).getSingleResult();
	}

	@Override
	public OperationalSystem getOSById(long osId) {
		Query query = em.createQuery("FROM OperationalSystem a WHERE a.osId=:osId");
		query.setParameter("osId", osId);

		return (OperationalSystem) query.getSingleResult();
	}

	@Override
	public OperationalSystem getOSByName(String osName) {
		Query query = em.createQuery("FROM OperationalSystem a WHERE a.operationalSystemName=:osName");
		query.setParameter("osName", osName);

		return (OperationalSystem) query.getSingleResult();
	}
	
/*	@Override
	public OperationalSystem getOSByDevice(Device device) {
		Query query = em.createQuery("FROM OperationalSystem a WHERE a.operationalSystemName=:osName");
		query.setParameter("osName", osName);

		return (OperationalSystem) query.getSingleResult();
	}
*/	
	@Override
	public List<OperationalSystem> getListOS() {
		return (List<OperationalSystem>) em.createQuery("FROM OperationalSystem").getResultList();
	}

	@Override
	public void newOS(String osName) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Computer> getComputers() {
		
		return (List<Computer>) em.createQuery("FROM Computer").getResultList();
	}

	@Override
	public Computer getComputer(long computerId) {
		Query query = em.createQuery("FROM Computer a WHERE a.computerId=:computerId");
		query.setParameter("computerId", computerId);

		return (Computer) query.getSingleResult();
	}

	/*
	 * @Override public List<Computer> getMobilePhonesByUser(long userId) {
	 * 
	 * return (List<Computer>)
	 * em.createQuery("SELECT u.computers FROM Users u where c.userId=:userId").
	 * setParameter("userId", userId).getResultList();
	 * 
	 * }
	 */

	@Override
	public void newComputer(String name, OperationalSystem os) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<MobilePhone> getMobilePhones() {

		return (List<MobilePhone>) em.createQuery("FROM MobilePhone").getResultList();

	}
	
	@Override
	public MobilePhone getMobilePhone(long mobilePhoneId) {
		Query query = em.createQuery("FROM MobilePhone a WHERE a.mobilePhoneId=:mobilePhoneId");
		query.setParameter("mobilePhoneId", mobilePhoneId);

		return (MobilePhone) query.getSingleResult();
	}

	@Override
	public void newMobilePhone(String name, OperationalSystem os) {
		// TODO Auto-generated method stub

	}

	/*
	 * @Override public List<Computer> getMobilePhonesByUser(long userId) {
	 * 
	 * return (List<Computer>)
	 * em.createQuery("SELECT u.computers FROM Users u where c.userId=:userId").
	 * setParameter("userId", userId).getResultList();
	 * 
	 * }
	 */

	@Override
	public List<Tablet> getTablets() {

		return (List<Tablet>) em.createQuery("FROM Tablet").getResultList();

	}

	@Override
	public Tablet getTablet(long tabletId) {
		Query query = em.createQuery("FROM Tablet a WHERE a.tabletId=:tabletId");
		query.setParameter("tabletId", tabletId);

		return (Tablet) query.getSingleResult();
	}

	@Override
	public void newTablet(String name, OperationalSystem os) {
		// TODO Auto-generated method stub

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
	public List<Device> getDevices(String osName) {
		List<Device> devices = new ArrayList<Device>();
/*
		OperationalSystem os = getOSByName(osName);

		devices.add((List<Device>) getComputersByOS(osName));
		devices.add((List<Device>) getMobilePhonesByOS(osName));
		devices.add( getTabletsByOS(osName));
*/
		return devices;

		}
	
	public void populate() {
		
		OperationalSystem os1 = new OperationalSystem("Ouindose");
		OperationalSystem os = new OperationalSystem("Linoux");
		
			User u1 = new User("PlaMic", "Platini", "Michel");

			Computer c1 = new Computer("Asus");
			c1.setOs(os);
			u1.addComputer(c1);
			em.persist(u1);
			
			User u2 = new User("ParTon", "Parker", "Tony");
			Computer c2 = new Computer("Dell");
			c2.setOs(os1);
			System.out.println("Trying to make 2nd user");
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
