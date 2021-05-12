package ch.hevs.networkservice;

import java.util.List;

import javax.ejb.Local;

import ch.hevs.businessobject.Computer;
import ch.hevs.businessobject.Device;
import ch.hevs.businessobject.MobilePhone;
import ch.hevs.businessobject.Tablet;
import ch.hevs.businessobject.User;

@Local
public interface Network {
	
	List<User> getUsers();
	
	User getUser(long userId);
	
	public List<Device> getAllDevicesByUserLogin(String loginName);
	
	void addComputer(Computer c, User u);
	
	void addTablet(Tablet t, User u);
	
	void addPhone(MobilePhone phone, User u);
	
	List<Computer> getComputers();
		

}
