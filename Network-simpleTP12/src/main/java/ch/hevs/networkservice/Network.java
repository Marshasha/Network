package ch.hevs.networkservice;

import java.util.List;

import javax.ejb.Local;

import ch.hevs.businessobject.Computer;

import ch.hevs.businessobject.User;

@Local
public interface Network {
	
	List<User> getUsers();
	
	User getUser(long userId);
	
	public List<Computer> getAllComputersByUserLogin(String loginName);
	
	void addComputer(Computer c, User u);
	
	void populate();
	
		

}
