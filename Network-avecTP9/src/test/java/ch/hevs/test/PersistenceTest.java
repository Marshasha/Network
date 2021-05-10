package ch.hevs.test;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;

import ch.hevs.businessobject.Computer;

import ch.hevs.businessobject.MobilePhone;
import ch.hevs.businessobject.OperationalSystem;
import ch.hevs.businessobject.User;

public class PersistenceTest {

	@Test
	public void test() {
		EntityTransaction tx = null;
		try {
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("networkPU");
			EntityManager em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			User u1 = new User("userMarina", "Marina");
			
			OperationalSystem os1 = new OperationalSystem("Windows");
			OperationalSystem os2 = new OperationalSystem("Android");
			
			u1.addDevice(new Computer("Asus", os1));
			u1.addDevice(new MobilePhone("Samsung", os2));

			


			em.persist(u1);
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();

			try {
				tx.rollback();
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			}

		}

	}
}
