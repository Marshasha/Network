package ch.hevs.bankservice;

import java.util.List;

import javax.ejb.Local;

import ch.hevs.businessobject.Computer;
import ch.hevs.businessobject.Device;
import ch.hevs.businessobject.MobilePhone;
import ch.hevs.businessobject.OperationalSystem;
import ch.hevs.businessobject.Tablet;
import ch.hevs.businessobject.Users;

@Local
public interface ITInfrastructure {

//	Users getUser(String accountDescription, String lastnameOwner);
	
//	public List<Users> getListofUsers(String lastname, String Firstname);

//	void newUsers(Account compteSrc, Account compteDest, int montant) throws Exception;

/*	public List<Device> getDevices();

	public Device getDevice(long deviceId);
	
	public void newDevice(String name, OperationalSystem os);
*/  //pas possible de jouer avec les devices ici (?)
	
	public List<Computer> getComputers();

	public Computer getComputer(long computerId);
	
	public void newComputer(String name, OperationalSystem os);
	
	public List<MobilePhone> getMobilePhones();

	public MobilePhone getMobilePhone(long MobilePhoneId);
	
	public void newMobilePhone(String name, OperationalSystem os);
	
	public List<Tablet> getTablets();

	public Tablet getTablet(long TabletId);
	
	public void newTablet (String name, OperationalSystem os);
	
	public OperationalSystem getOS(long osId);
	
	public List<OperationalSystem> getListOS();
	
	public void newOS(String osName);

	
}
