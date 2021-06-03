package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.Query;

import ch.hevs.businessobject.Computer;
import ch.hevs.businessobject.Device;
import ch.hevs.businessobject.MobilePhone;
import ch.hevs.businessobject.OperationalSystem;
import ch.hevs.businessobject.Tablet;
import ch.hevs.businessobject.User;
import ch.hevs.networkservice.Network;

public class DistributionBean {

	private Network net;

	private User user;
	private List<String> userLoginNames;
	private String userloginName;
	private String userName;
//	private List<String> userNames;
	private List<User> users;
//	private String userDescription;

	private Device device;
	private String deviceName;
	private String deviceDescription;
	private List<String> deviceNames;
	private List<String> deviceDescriptions;
	private List<Device> devices = new ArrayList<Device>();

	private Computer computer;
	private List<String> computerNames;
	private String computerName;
	private List<Computer> computers;

	private MobilePhone phone;
	private List<String> phoneNames;
	private String phoneName;
	private List<MobilePhone> phones;

	private Tablet tablet;
	private List<String> tabletrNames;
	private String tabletName;
	private List<String> tabletNames;
	private List<Tablet> tablets;

	private String osName;
	private OperationalSystem os;
	private List<OperationalSystem> OSList;
	private List<String> osNames;

//	private List<Object> transactionresult;

	@PostConstruct
	public void initialize() throws NamingException {

		InitialContext ctx = new InitialContext();
		net = (Network) ctx
				.lookup("java:global/Network-simpleTP12-0.0.1-SNAPSHOT/NetworkBean!ch.hevs.networkservice.Network");

		/** 
		 * Méthode populate() à activer/désactiver selon volonté
		 */
		net.populate();
		
		List<User> userList = net.getUsers();
		this.userLoginNames = new ArrayList<String>();
		for (User user : userList) {
			this.userLoginNames.add(user.getLoginName());
		}

		getAllDevices();

		List<OperationalSystem> osList = net.getListOS();
		this.osNames = new ArrayList<String>();
		for (OperationalSystem os : osList) {
			this.osNames.add(os.getOperationalSystemName());
		}

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

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public OperationalSystem getOs() {
		return os;
	}

	public void setOs(OperationalSystem os) {
		this.os = os;
	}

	public List<String> getDeviceNames() {
		return deviceNames;
	}

	public void setDeviceNames(List<String> deviceNames) {
		this.deviceNames = deviceNames;
	}

	public List<String> getComputerNames() {
		return computerNames;
	}

	public void setComputerNames(List<String> computerNames) {
		this.computerNames = computerNames;
	}

	public List<String> getPhoneNames() {
		return phoneNames;
	}

	public void setPhoneNames(List<String> phoneNames) {
		this.phoneNames = phoneNames;
	}

	public List<String> getTabletNames() {
		return tabletNames;
	}

	public void setTabletNames(List<String> tabletNames) {
		this.tabletNames = tabletNames;
	}

	public List<OperationalSystem> getOSList() {
		return OSList;
	}

	public void setOSList(List<OperationalSystem> oSList) {
		OSList = oSList;
	}

	public List<Device> getDevices() {
		return this.devices;
	}

	public void setDevices(List<Device> devicesList) {
		this.devices = devicesList;
	}

	public List<String> getOsNames() {
		return osNames;
	}

	public void setOsNames(List<String> osNames) {
		this.osNames = osNames;
	}

	// Appels
	public void listOfOpSsyt(ValueChangeEvent event) {
		this.osName = (String) event.getNewValue();

		List<OperationalSystem> osList = net.getListOS();
		this.osNames = new ArrayList<String>();
		for (OperationalSystem os : osList) {
			this.osNames.add(os.getOperationalSystemName());
		}
	}

	public void getDevicesByUser(ValueChangeEvent event) {
		this.userloginName = (String) event.getNewValue();

		List<Computer> userDevices = net.getAllComputersByUserLogin(this.userloginName);

		this.computerNames = new ArrayList<String>();
		for (Computer comp : userDevices) {
			this.computerNames.add(comp.getName());
		}

	}

	public String showAllDevicesData() {

		for (String userComp : computerNames) {

			this.computerName = userComp;

		}

		return "Show all user devices data";
	}

	public void getAllDevices() {
		computers = net.getComputers();
		phones = net.getMobilePhones();
		tablets = net.getTablets();

		composeDeviceList(computers, phones, tablets);
		
/*		devices = net.getDevices();
		composeDeviceLists(devices);	
		*/	
	}
	
	

	public void listOfDevicesByOS(ValueChangeEvent event) {
		this.osName = (String) event.getNewValue();
		devices = new ArrayList<Device>();

		computers = new ArrayList<Computer>();
		phones = new ArrayList<MobilePhone>();
		tablets = new ArrayList<Tablet>();

		
		if (isValidParameter(osName) || osName != "All operating System") {
			
/*			devices = net.getDeviceByOs(osName);

			composeDeviceLists(devices);
*/
			
			computers = net.getComputersByOS(osName);
			phones = net.getMobilePhonesByOS(osName);
			tablets = net.getTabletsByOS(osName);

			composeDeviceList(computers, phones, tablets);

		}

		if (!devices.isEmpty()) {
			devices.add(new Device("No Device"));
		}

	}

	public String performHaveALook() {
//    	this.osName = (String)event.getNewValue();

//    	if (isInvalidParam(osName)) {
		/*
		 * computers = net.getComputersByOS(this.os); phones =
		 * net.getMobilePhonesByOS(this.os); tablets = net.getTabletsByOS(this.os);
		 * 
		 * composeDeviceList (computers, phones, tablets); // }
		 * 
		 * /* if (!devices.isEmpty()) getDevice();
		 */
		// if(devices.isEmpty())

		return "justHaveALookOnPark";
	}

	public String displaylistOfUsersByDevice() {
		
		return "showUserOfDevice";
	}
	
	private boolean isValidParameter(String value) {

		if (value != null || value != "")
			return true;

		return false;
	}

/*	public void listOfUsersByDevice( ValueChangeEvent event) {
//		this.deviceName = (String) event.getNewValue();

		for (Device device : devices) {
			if (deviceName == device.getName()) {
				this.device = device;
				break;
			}

		}
		
		if (device != null) {			
			List<User> usereList = net.getUserByDevice(this.device);
			this.userLoginNames = new ArrayList<String>();
			for (User user : usereList) {
				this.userLoginNames.add(user.getLoginName());
			}
		}

	}
*/	
	public String showUsersByDevice() {
		
//		listOfUsersByDevice(deviceName);
		
		for (Device device : devices) {
			if (deviceName == device.getName()) {
				this.device = device;
				break;
			}

		}
		
		if (device != null) {			
			List<User> usereList = net.getUserByDevice(this.device);
			this.userLoginNames = new ArrayList<String>();
			for (User user : usereList) {
				this.userLoginNames.add(user.getLoginName());
			}
		}
		
		return "showUsersByDevice";
	}
	
	
	public void deleteEntity(Object object /* ValueChangeEvent event */) {
//		Object obj = new Object ();
		String className = object.getClass().toString();		
		String objectName = "";
			
		// Série de "if" car impossible de faire un "switch/case" sur un .getClass()
		if (object.getClass().equals(User.class)) {
			User obj = (User) object;
			className = obj.getClass().toString();
			objectName = obj.getLoginName();
		}		
		else if (object.getClass().equals(OperationalSystem.class)) {
			OperationalSystem obj = (OperationalSystem) object;
			className = obj.getClass().toString();
			objectName = obj.getOperationalSystemName();
		}		
		else if (object.getClass().equals(Computer.class)) {
			Computer obj = (Computer) object;
			className = obj.getClass().toString();
			objectName = obj.getName();
		}		
		else if (object.getClass().equals(MobilePhone.class)) {
			MobilePhone obj = (MobilePhone) object;
			className = obj.getClass().toString();
			objectName = obj.getName();		
			}		
		else if (object.getClass().equals(Tablet.class)) {
			Tablet obj = (Tablet) object;
			className = obj.getClass().toString();
			objectName = obj.getName();
			}

		className = className.substring(className.lastIndexOf(".")+1);
		System.out.println(" className  :  " + className.toUpperCase());
		
		net.deleteEntity( object, className /* , objectName */ );
		
	}

	public void listOfDevicesByUser(ValueChangeEvent event) {
		this.userloginName = (String) event.getNewValue();
		/*
		 * computers = net.getComputerByUser(this.userloginName); phones =
		 * net.getMobilePhoneByUser(this.userloginName); tablets =
		 * net.getTabletByUser(this.userloginName);
		 */
//		composeDeviceList(computers, phones, tablets);
		
		
		
		composeDeviceLists(devices);


	}


	public void composeDeviceList(List<Computer> computerList, List<MobilePhone> mobilePhoneList,
			List<Tablet> tabletList) {
		this.devices = new ArrayList<Device>();

		this.deviceNames = new ArrayList<String>();
		for (Computer device : computerList) {
			this.deviceNames.add(device.getName());
			this.devices.add(device);
		}
		for (MobilePhone device : mobilePhoneList) {
			this.deviceNames.add(device.getName());
			this.devices.add(device);
		}
		for (Tablet device : tabletList) {
			this.deviceNames.add(device.getName());
			this.devices.add(device);
		}

	}

	public void composeDeviceLists(List<Device> devicesList) {

		this.deviceNames = new ArrayList<String>();
		this.computers = new ArrayList<Computer>();
		this.computerNames = new ArrayList<String>();
		this.phones = new ArrayList<MobilePhone>();
		this.phoneNames = new ArrayList<String>();
		this.tablets = new ArrayList<Tablet>();
		this.tabletNames = new ArrayList<String>();
		
		for (Device device : devices) {
			this.deviceNames.add(device.getName());
			
			if(device.getClass().equals(Computer.class)) {
				this.computerNames.add(device.getName());
				this.computers.add((Computer) device);
			}
			if(device.getClass().equals(MobilePhone.class)) {
				this.phoneNames.add(device.getName());
				this.phones.add((MobilePhone) device);
			}
			if(device.getClass().equals(Tablet.class)) {
				this.tabletNames.add(device.getName());
				this.tablets.add((Tablet) device);
			}
				
		}
		
/*			
		for (Computer device : computerList) {
			this.deviceNames.add(device.getName());
			this.devices.add(device);
		}
		for (MobilePhone device : mobilePhoneList) {
			this.deviceNames.add(device.getName());
			this.devices.add(device);
		}
		for (Tablet device : tabletList) {
			this.deviceNames.add(device.getName());
			this.devices.add(device);
		}
*/
	}
	

	public void updateDeviceNamesFromUser(ValueChangeEvent event) {
		this.userloginName = (String) event.getNewValue();

	}

	public void updateOSNamesFromDevice(ValueChangeEvent event) {
		deviceName = (String) event.getNewValue();

	}

	public void getDeviceOS(ValueChangeEvent event) {
		device = (Device) event.getNewValue();

		os = device.getOs();

		osName = os.getOperationalSystemName();

		os = device.getOs();
	}

}
