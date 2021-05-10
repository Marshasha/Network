package ch.hevs.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;

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

			User u = new User();
			u.setLoginName("UserMarina");
			u.setName("Marina");
					
			em.persist(u);

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
