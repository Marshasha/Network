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
import ch.hevs.exception.NetworkException;
import ch.hevs.networkservice.Network;

public class DistributionBean {

	private Network net;
	private String transactionResult;
	private String CallerInRole;	
	private String classObject = "";
	private String errorMsg = "";
	
	private User user;
	private List<String> userLoginNames = new ArrayList<String>();
	private String userName;
	private List<User> users;
	
	private String userloginName;
	private String firstName;
	private String lastName;
	
	private Device device;
	private String deviceName = "";
	private List<String> deviceNames;
	private List<Device> devices = new ArrayList<Device>();
	private String categoryOfDevice = "";
	
	private Computer computer;
	private String computerName;
	private List<Computer> computers = new ArrayList<Computer>();

	private MobilePhone phone;
	private String phoneName;
	private List<MobilePhone> phones = new ArrayList<MobilePhone>();

	private Tablet tablet;
	private String tabletName;
	private List<Tablet> tablets = new ArrayList<Tablet>();

	private String osName = "";
	private OperationalSystem os;
	private List<String> osNames;


	@PostConstruct
	public void initialize() throws NamingException {

		InitialContext ctx = new InitialContext();
		net = (Network) ctx
				.lookup("java:global/Network-simpleTP12-0.0.1-SNAPSHOT/NetworkBean!ch.hevs.networkservice.Network");

		users = net.getUsers();
		this.userLoginNames = new ArrayList<String>();
		for (User user : users) {
			this.userLoginNames.add(user.getLoginName());
		}

		getAllDevices();

		List<OperationalSystem> osList = net.getListOS();
		this.osNames = new ArrayList<String>();
		for (OperationalSystem os : osList) {
			this.osNames.add(os.getOperationalSystemName());
		}

	}

	/**
	 * Getters/Setters
	 * 
	 * @return
	 */

	public List<String> getUserLoginNames() {
		return userLoginNames;
	}

	public String getUserloginName() {
		return userloginName;
	}

	public void setUserloginName(String userloginName) {
		this.userloginName = userloginName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setUserLoginNames(List<String> userLoginNames) {
		this.userLoginNames = userLoginNames;
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

	public String getCategoryOfDevice() {
		return categoryOfDevice;
	}

	public void setCategoryOfDevice(String categoryOfDevice) {
		this.categoryOfDevice = categoryOfDevice;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<User> getUsers() {
		return this.users;
	}
	
	public Computer getComputer() {
		return computer;
	}

	public void setComputer(Computer computer) {
		this.computer = computer;
	}

	public MobilePhone getPhone() {
		return phone;
	}

	public void setPhone(MobilePhone phone) {
		this.phone = phone;
	}

	public List<MobilePhone> getPhones() {
		return phones;
	}

	public void setPhones(List<MobilePhone> phones) {
		this.phones = phones;
	}

	public Tablet getTablet() {
		return tablet;
	}

	public void setTablet(Tablet tablet) {
		this.tablet = tablet;
	}

	public List<Tablet> getTablets() {
		return tablets;
	}

	public void setTablets(List<Tablet> tablets) {
		this.tablets = tablets;
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

	public String getClassObject() {
		return classObject;
	}

	public void setClassObject(String classObject) {
		this.classObject = classObject;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * Appels
	 * 
	 * @param event
	 */
	// USER
	public List<User> listOfUsersByDevice(Device device) {

		return net.getUsersByDevice(device);
	}
	
	public void getAllUsers() {
		users = net.getUsers();
	}
	
	public void getChoosedUser(ValueChangeEvent event) {		
		String loging = (String) event.getNewValue();
		
		userloginName = loging;
		
		user = net.getUserByLoginName(userloginName);
		
		getDevicesByUser(user);
	}
	
	// OPERATING SYSTEM
	public void listOfOpSsyt(ValueChangeEvent event) {
		this.osName = (String) event.getNewValue();

		List<OperationalSystem> osList = net.getListOS();
		this.osNames = new ArrayList<String>();
		for (OperationalSystem os : osList) {
			this.osNames.add(os.getOperationalSystemName());
		}
	}
	
	public String categoryOfDevice (Device device) {
				
		String className = device.getClass().toString();
		categoryOfDevice = className.substring(className.lastIndexOf(".")+1);
					
		return categoryOfDevice;
	}

	// DEVICE
	public String addDevice() {

		net.addDevice(deviceName, classObject, osName);

		return "showAdminAccount";
	}
	
	public List<Device> getDevicesByUser(User user) {
		
		devices = net.getDevicesByUser(user);
		
		return devices;		
	}
	
	public void listOfDevicesByOS(ValueChangeEvent event) {
		this.osName = (String) event.getNewValue();

		getDevicesByOS(osName);

	}	

	private void getDevicesByOS(String osName) {
		devices = new ArrayList<Device>();

		computers = new ArrayList<Computer>();
		phones = new ArrayList<MobilePhone>();
		tablets = new ArrayList<Tablet>();

		if (isValidParameter(osName) || osName != "All operating System") {
			computers = net.getComputersByOS(osName);
			phones = net.getMobilePhonesByOS(osName);
			tablets = net.getTabletsByOS(osName);

			composeDeviceList(computers, phones, tablets);
		}

		if (!devices.isEmpty()) {
			devices.add(new Device("No Device"));
		}
	}

	public void getAllDevices() {
		computers = net.getComputers();
		phones = net.getMobilePhones();
		tablets = net.getTablets();

		composeDeviceList(computers, phones, tablets);

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
		this.phones = new ArrayList<MobilePhone>();
		this.tablets = new ArrayList<Tablet>();

		for (Device device : devices) {
			this.deviceNames.add(device.getName());

			if (device.getClass().equals(Computer.class)) {
				this.computers.add((Computer) device);
			}
			if (device.getClass().equals(MobilePhone.class)) {
				this.phones.add((MobilePhone) device);
			}
			if (device.getClass().equals(Tablet.class)) {
				this.tablets.add((Tablet) device);
			}
		}
	}

	private boolean isValidParameter(String value) {

		if (value != null || value != "")
			return true;

		return false;
	}

	public void deleteDevice(Device object) {
		deleteEntity(object);
	}

	public void deleteEntity(Object object) {
		net.deleteEntity(object);
	}

	/**
	 * Display
	 * 
	 * @return
	 */

	public String performHaveALook() {

		return "justHaveALookOnPark";
	}

	public String updateHaveALook(Device device) {

		deleteDevice(device);
		getDevicesByOS(osName);

		return "justHaveALookOnPark";
	}

	public String showUsersByDevice(Device device) {

		users = listOfUsersByDevice(device);
			
		return "showUsersOfDevice";
	}

	public String showMyDevices() {

		getDevices();
		
		return "showDeviceOfConnectedUser";
	}
	
	public String  showUserDetailForManager (User u) {
		
		user = u;
		
		return "showUserDetailForManager";
	}
	
	private String populateResult(String msg) {		
		this.errorMsg = msg;
		
		return "showPopulateResult";
	}

	public String performInsertUser() {		
		try {
			if ((firstName.isEmpty()) || (lastName.isEmpty())) {
				
				this.transactionResult="Error: please fill in the forms!";
			} 
			else {
				
			net.addUser(firstName, lastName); 
			this.transactionResult="Success!";
			}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
		
		
		return "showInsertUserResult";
	}

	public String populate() {
		
		if(net.getListOS().isEmpty()) {	
			net.populate();
			errorMsg ="            Database is OK. \nYou must deploy again Wildfly !";
		}
		else
		errorMsg ="          Database contains enough elements !";
				
		
		return "showPopulateResult";
	
	}

}
