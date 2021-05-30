package ch.hevs.networkservice;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import ch.hevs.businessobject.Computer;
import ch.hevs.businessobject.OperationalSystem;
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

	public void populate() {
		

			User u1 = new User("PlaMic", "Platini", "Michel");

			Computer c1 = new Computer("Asus");
			OperationalSystem os1 = new OperationalSystem("Windows");
			c1.setOs(os1);
			u1.addComputer(c1);
			em.persist(u1);
			
			User u2 = new User("ParTon", "Parker", "Tony");
			Computer c2 = new Computer("Dell");
			System.out.println("Trying to make 2nd user");
			u2.addComputer(c2);			
			em.persist(u2);
	
	

	}
	

	

	

}
