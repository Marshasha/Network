package ch.hevs.networkservice;

import java.util.List;

import javax.ejb.Local;

import ch.hevs.businessobject.Computer;
import ch.hevs.businessobject.Device;
import ch.hevs.businessobject.MobilePhone;
import ch.hevs.businessobject.OperationalSystem;
import ch.hevs.businessobject.Tablet;
import ch.hevs.businessobject.User;

@Local
public interface Network {
	
	List<User> getUsers();
	
	User getUser(long userId);
	
	public List<Computer> getAllComputersByUserLogin(String loginName);
	
	void addComputer(Computer c, User u);
	
	public List<Computer> getComputers();

	public Computer getComputer(long computerId);
	
/*	public List<Computer> getComputerByUser(String userLoginName);
	
	public  List<MobilePhone> getMobilePhoneByUser(String userLoginName);
	
	public  List<Tablet> getTabletByUser(String userLoginName);
*/
	public void newComputer(String name, OperationalSystem os);

	public List<MobilePhone> getMobilePhones();

	public MobilePhone getMobilePhone(long MobilePhoneId);

	public void newMobilePhone(String name, OperationalSystem os);

	public List<Tablet> getTablets();

	public Tablet getTablet(long TabletId);

	public void newTablet(String name, OperationalSystem os);
	
	public List<User> getUserByDevice(Device device);

	public List<Computer> getComputersByOS(String os);

	public List<Tablet> getTabletsByOS(String os);

	public List<MobilePhone> getMobilePhonesByOS(String os);

	public List<Device> getDevices();
	
	public List<Device> getDeviceByOs(String osName);

	public OperationalSystem getOSById(long osId);

	public OperationalSystem getOSByName(String osName);

	public List<OperationalSystem> getListOS();

	public void newOS(String osName);
	
	public void deleteEntity(Object object, String className /* , String objectName */);
	
	public void populate();	
		
}
