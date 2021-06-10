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
	
	public List<User> getUsers();

	public User getUserByLoginName(String userloginName);
	
	public User addUser(String firstName, String lastName);

	public void addDevice(String deviceName, String deviceClass, String osName);
		
	public List<Device> getDevicesByUser (User user);
	
	public List<Computer> getComputers();

	public Computer getComputerById(long computerId);
	
	public List<MobilePhone> getMobilePhones();

	public List<Tablet> getTablets();
	
	public List<User> getUsersByDevice(Device device);

	public List<Computer> getComputersByOS(String os);

	public List<Tablet> getTabletsByOS(String os);

	public List<MobilePhone> getMobilePhonesByOS(String os);

	public List<Device> getDevices();
	
	public Device getDeviceById(long id);

	public OperationalSystem getOsNameByDevice(Device device);

	public OperationalSystem getOSByName(String osName);

	public List<OperationalSystem> getListOS();

	
	public void deleteEntity(Object object);
	
	public void populate();	
		
}
