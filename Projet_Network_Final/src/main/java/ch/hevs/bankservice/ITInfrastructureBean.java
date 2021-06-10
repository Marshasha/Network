package ch.hevs.bankservice;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import ch.hevs.businessobject.Account;
import ch.hevs.businessobject.Computer;
import ch.hevs.businessobject.Device;
import ch.hevs.businessobject.MobilePhone;
import ch.hevs.businessobject.OperationalSystem;
import ch.hevs.businessobject.Tablet;


//@stateful
 //@staless  // >> la classe ne permet qu'un seul appel
//@TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
public class ITInfrastructureBean implements ITInfrastructure {

//	@PersistenceContext(name = "BankPU", type = PersistenceContextType.EXTENDED)
	@PersistenceContext(name = "networkPU", type = PersistenceContextType.TRANSACTION)
	private EntityManager em;

	
/*	
	@Override
	public List<Device> getDevices() {
		
		return (List<Device>) em.createQuery("SELECT * FROM Device d").getResultList();
	}

	@Override
	public Device getDevice(long deviceId) {
		Query query = em.createQuery("FROM Account a WHERE a.deviceId=:deviceId");
		query.setParameter("deviceId", deviceId);
//		query.setParameter("name", name);
		
		return (Device) query.getSingleResult();
	}

	@Override
	public void newDevice(String name, OperationalSystem os) {
		// TODO Auto-generated method stub
		
	}
*/
	@Override
	public OperationalSystem getOS(long osId) {
		Query query = em.createQuery("FROM OperationalSystem a WHERE a.osId=:osId");
		query.setParameter("osId", osId);
		
		return (OperationalSystem) query.getSingleResult();
	}

	@Override
	public List<OperationalSystem> getListOS() {
		return em.createQuery("FROM OperationalSystem").getResultList();
	}

	@Override
	public void newOS(String osName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Computer> getComputers() {

		return em.createQuery("FROM Computer").getResultList();

	}

	@Override
	public Computer getComputer(long computerId) {
		Query query = em.createQuery("FROM Computer a WHERE a.computerId=:computerId");
		query.setParameter("computerId", computerId);
		
		return (Computer) query.getSingleResult();
	}
	
	/*	@Override
	public List<Computer> getMobilePhonesByUser(long userId) {

		return (List<Computer>) em.createQuery("SELECT u.computers FROM Users u where c.userId=:userId").setParameter("userId", userId).getResultList();

	}
*/

	@Override
	public void newComputer(String name, OperationalSystem os) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<MobilePhone> getMobilePhones() {

		return em.createQuery("FROM MobilePhone").getResultList();

	}

/*	@Override
	public List<MobilePhone> getMobilePhonesByUser(long userId) {

		return (List<MobilePhone>) em.createQuery("SELECT u.mobilephoneId FROM Users u where c.userId=:userId").setParameter("userId", userId).getResultList();

	}
*/
	
	
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

	/*	@Override
	public List<Computer> getMobilePhonesByUser(long userId) {

		return (List<Computer>) em.createQuery("SELECT u.computers FROM Users u where c.userId=:userId").setParameter("userId", userId).getResultList();

	}
*/
	
	@Override
	public List<Tablet> getTablets() {

		return em.createQuery("FROM Tablet").getResultList();

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


}
