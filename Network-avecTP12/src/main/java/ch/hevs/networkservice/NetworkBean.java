package ch.hevs.networkservice;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import ch.hevs.businessobject.Computer;
import ch.hevs.businessobject.Device;
import ch.hevs.businessobject.MobilePhone;
import ch.hevs.businessobject.Tablet;
import ch.hevs.businessobject.User;

@Stateless
public class NetworkBean implements Network {
	
	@PersistenceContext(name = "networkPU", type=PersistenceContextType.TRANSACTION)
	private EntityManager em;

	public Computer getComputers(String userLoginName) {
		Query query = em.createQuery("FROM Computer c WHERE c.owner.userLoginName=:userLoginName");
		query.setParameter("userLoginName", userLoginName);

		return (Computer) query.getSingleResult();
	}

	public List<Device> getAllDevicesByUserLogin(String loginName) {
		
		return (List<Device>) em.createQuery("SELECT u.devices FROM USER u WHERE u.loginname=:loginname")
				.setParameter("loginname", loginName).getResultList();
	}

	public void addComputer(Computer c, User u) {

		em.persist(c);
		em.persist(u);
		u.addComputer(c);
		c.addOwner(u);
		
	}
	
	public List<User> getUsers() {		
		return (List<User>) em.createQuery("FROM User").getSingleResult();
	}

	public User getUser(long userId) {
		
		return (User) em.createQuery("FROM User u where u.userId=:userId").setParameter("userId", userId).getSingleResult();
	}

	@Override
	public List<Computer> getComputers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addTablet(Tablet t, User u) {

		em.persist(t);
		em.persist(u);
		u.addTablet(t);
		t.addOwner(u);
		
	}

	@Override
	public void addPhone(MobilePhone phone, User u) {
		em.persist(phone);
		em.persist(u);
		u.addPhone(phone);
		phone.addOwner(u);
		
	}

	

}
