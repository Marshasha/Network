package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.businessobject.Computer;
import ch.hevs.businessobject.Device;
import ch.hevs.businessobject.User;
import ch.hevs.networkservice.Network;

public class DistributionBean {
	
	private List<String> userLoginNames;
	private List<Device> userDevices;
	private List<String> deviceNames;
	private String deviceName;
	private String userloginName;
	private Network net;
	
	public void initialize() throws NamingException {
		
		InitialContext ctx = new InitialContext();
		net = (Network) ctx.lookup("java:global/Network-avecTP12-0.0.1-SNAPSHOT/NetworkBean!ch.hevs.networkservice.Network");
		
		List<User> userList = net.getUsers();
		this.userLoginNames = new ArrayList<String>();
		for(User user : userList) {
			this.userLoginNames.add(user.getLoginName());
		}
		
		this.deviceNames = new ArrayList<String>();
		userDevices = net.getAllDevicesByUserLogin(userList.get(0).getLoginName());
		this.deviceNames.add(userDevices.get(0).getName());
		
	}
	
	
	public List<String> getUserLoginNames() {
		return userLoginNames;
	}


	public void setUserLoginNames(List<String> userLoginNames) {
		this.userLoginNames = userLoginNames;
	}


	public List<Device> getUserDevices() {
		return userDevices;
	}


	public void setUserDevices(List<Device> userDevices) {
		this.userDevices = userDevices;
	}


	public List<String> getDeviceNames() {
		return deviceNames;
	}


	public void setDeviceNames(List<String> deviceNames) {
		this.deviceNames = deviceNames;
	}


	public String getUserloginName() {
		return userloginName;
	}


	public void setUserloginName(String userloginName) {
		this.userloginName = userloginName;
	}


	public void showAllDevicesPerUser(ValueChangeEvent event) {
		this.userloginName = (String)event.getNewValue();
		
		List<Device> userDevices = net.getAllDevicesByUserLogin(this.userloginName);
		
		this.deviceNames = new ArrayList<String>();
		for(Device device : userDevices) {
			this.deviceNames.add(device.getName());
		} 
		
	}


	public String getDeviceName() {
		return deviceName;
	}


	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	public String showAllDevicesData() {
		
		for(Device userDevice : userDevices) {
			
			this.deviceName=userDevice.getName();
			
		}
		
		return "Show all user devices data";
	}

}
