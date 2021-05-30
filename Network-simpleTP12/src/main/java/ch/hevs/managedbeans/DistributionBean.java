package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.businessobject.Computer;

import ch.hevs.businessobject.User;
import ch.hevs.networkservice.Network;

public class DistributionBean {
	
	private List<String> userLoginNames;
	private List<String> computerNames;
	private String computerName;
	private String userloginName;
	private Network net;
	
	@PostConstruct
	public void initialize() throws NamingException {
		
		InitialContext ctx = new InitialContext();
		net = (Network) ctx.lookup("java:global/Network-simpleTP12-0.0.1-SNAPSHOT/NetworkBean!ch.hevs.networkservice.Network");
		net.populate();
		
		List<User> userList = net.getUsers();
		this.userLoginNames = new ArrayList<String>();
		for(User user : userList) {
			this.userLoginNames.add(user.getLoginName());
		}
		
		this.computerNames = new ArrayList<String>();
		List <Computer> userComputers = net.getAllComputersByUserLogin(userList.get(0).getLoginName());
		this.computerNames.add(userComputers.get(0).getName());
		
	}
	
	
	public List<String> getUserLoginNames() {
		return userLoginNames;
	}


	public void setUserLoginNames(List<String> userLoginNames) {
		this.userLoginNames = userLoginNames;
	}

	public String getUserloginName() {
		return userloginName;
	}


	public void setUserloginName(String userloginName) {
		this.userloginName = userloginName;
	}


	public void showAllDevicesPerUser(ValueChangeEvent event) {
		this.userloginName = (String)event.getNewValue();
		
		List<Computer> userDevices = net.getAllComputersByUserLogin(this.userloginName);
		
		this.computerNames = new ArrayList<String>();
		for(Computer comp : userDevices) {
			this.computerNames.add(comp.getName());
		} 
		
	}

	
	public String showAllDevicesData() {
		
		for(String userComp : computerNames) {
			
			this.computerName=userComp;
			
		}
		
		return "Show all user devices data";
	}

}
