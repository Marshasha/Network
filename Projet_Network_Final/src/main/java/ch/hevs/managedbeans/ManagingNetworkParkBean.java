package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.bankservice.Bank;
import ch.hevs.bankservice.ITInfrastructure;
import ch.hevs.businessobject.*;

public class ManagingNetworkParkBean {
	
	private String osNname;
	private String deviceName;
	private String userName;
	
	private Device device;
	private OperationalSystem os;
//	private User user;
	
	
	private List<OperationalSystem> OSList;
	private List<Device> devicesList;
	 private List<String> osNames;

	 private List<String> userNames;
	    private List<String> deviceDescriptions;
	    private List<String> userDescriptions;
	    private String deviceDescription;
	    private String userDescription;

	    private List <Object> transactionResult;
	    private ITInfrastructure itInfrastructure;
	
	    
	    @PostConstruct
/*	    public void initialize() throws NamingException {
	    	
	    	// use JNDI to inject reference to bank EJB
	    	InitialContext ctx = new InitialContext();
			bank = (Bank) ctx.lookup("java:global/TP12-WEB-EJB-PC-EPC-E-0.0.1-SNAPSHOT/BankBean!ch.hevs.bankservice.Bank");    	
				
	    	// get clients
			List<Client> clientList = bank.getClients();
			this.clientNames = new ArrayList<String>();
			for (Client client : clientList) {
				this.clientNames.add(client.getLastname());
			}
			
			// initialize account descriptions
			this.sourceAccountDescriptions = new ArrayList<String>();
			this.destinationAccountDescriptions = new ArrayList<String>();
			List<Account> accounts = bank.getAccountListFromClientLastname(clientList.get(0).getLastname());
			this.sourceAccountDescriptions.add(accounts.get(0).getDescription());
			this.destinationAccountDescriptions.add(accounts.get(0).getDescription());
	    }
*/
	    public void initialize() throws NamingException {
	    	
	    	// use JNDI to inject reference to bank EJB
	    	InitialContext ctx = new InitialContext();
	    	itInfrastructure = (ITInfrastructure) ctx.lookup("java:global/TP12-WEB-EJB-PC-EPC-E-0.0.1-SNAPSHOT/BankBean!ch.hevs.bankservice.Bank");    	
				
	    	// get OSs
	    	List<OperationalSystem> osList = itInfrastructure.getListOS();
			this.osNames = new ArrayList<String>();
			for (OperationalSystem os : osList) {
				this.osNames.add(os.getOperationalSystemName());
			}
			
			// get Devices
			List<Computer> computerList = itInfrastructure.getComputers();
			List<MobilePhone> mobilePhoneList = itInfrastructure.getMobilePhones();
			List<Tablet>tabletList = itInfrastructure.getTablets();

			this.deviceDescriptions = new ArrayList<String>();
			for (Device device : computerList) {
				this.deviceDescriptions.add(device.getName());
			}
			for (Device device : mobilePhoneList) {
				this.deviceDescriptions.add(device.getName());
			}
			for (Device device : tabletList) {
				this.deviceDescriptions.add(device.getName());
			}
			
			// get Users
/*			List<Users> userList = itInfrastructure.;
			this.osNames = new ArrayList<String>();
			for (OperationalSystem os : osList) {
				this.osNames.add(os.getOperationalSystemName());
			}
*/			
			// initialize account descriptions
			
	    	
	    	
	    }
	    
//	méthode devicesName;
//	méthode usersName;  
//	méthode listOfUsersByDevice;
	    
	    // devices
	    public Device getDeviceSelected () {
	    	return device;
	    }
	    public void setDeviceSelected (final Device device) {
	    	this.device = device;
	    }
	    
/*	    // sourceClientName
	    public String getSourceClientName () {
	    	return sourceClientName;
	    }
	    public void setSourceClientName (final String sourceClientName) {
	    	this.sourceClientName=sourceClientName;
	    }
	    
	    // sourceAccountDescriptions
	    public List<String> getSourceAccountDescriptions () {
	    	return sourceAccountDescriptions;
	    }
	    
	    // destinationAccountDescriptions
	    public List<String> getDestinationAccountDescriptions () {
	    	return destinationAccountDescriptions;
	    }
	    
	    // destinationClientName
	    public String getDestinationClientName () {
	    	return destinationClientName;
	    }
	    public void setDestinationClientName (final String destinationClientName) {
	    	this.destinationClientName=destinationClientName;
	    }
	    
	    // transactionResult
	    public String getTransactionResult () {
	    	return transactionResult;
	    }
		public void setTransactionResult(String transactionResult) {
			this.transactionResult = transactionResult;
		}
	    
		// sourceAccountDescription
	    public String getSourceAccountDescription() {
			return sourceAccountDescription;
		}
		public void setSourceAccountDescription(String sourceAccountDescription) {
			this.sourceAccountDescription = sourceAccountDescription;
		}

		// destinationAccountDescription
		public String getDestinationAccountDescription() {
			return destinationAccountDescription;
		}
		public void setDestinationAccountDescription(
				String destinationAccountDescription) {
			this.destinationAccountDescription = destinationAccountDescription;
		}

		public void updateSourceAccounts(ValueChangeEvent event) {
	    	this.sourceClientName = (String)event.getNewValue();
	    	
		    List<Account> accounts = bank.getAccountListFromClientLastname(this.sourceClientName);
		    this.sourceAccountDescriptions = new ArrayList<String>();
			for (Account account : accounts) {
				this.sourceAccountDescriptions.add(account.getDescription());
			}
	    }
		public void updateDestinationAccounts(ValueChangeEvent event) {
	    	this.destinationClientName = (String)event.getNewValue();
				
		    List<Account> accounts = bank.getAccountListFromClientLastname(this.destinationClientName);
		    this.destinationAccountDescriptions = new ArrayList<String>();
			for (Account account : accounts) {
				this.destinationAccountDescriptions.add(account.getDescription());
			}
	    }

	    public List<Client> getClients() {
			return clients;
	    }
	    
	    public List<String> getClientNames() {
	    	return clientNames;
	    }
	    
	    
	    public String performTransfer() {
	    	
	    	try {
				if (sourceClientName.equals(destinationClientName) && sourceAccountDescription.equals(destinationAccountDescription)) {
					
					this.transactionResult="Error: accounts are identical!";
				} 
				else {
					
					Account compteSrc = bank.getAccount(sourceAccountDescription, sourceClientName);
					Account compteDest = bank.getAccount(destinationAccountDescription, destinationClientName);
		
					// Transfer
					bank.transfer(compteSrc, compteDest, transactionAmount);
					this.transactionResult="Success!";
				}
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}

			return "showTransferResult"; //  the String value returned represents the outcome used by the navigation handler to determine what page to display next.
		}   
	    
	   
//	méthode listOfDevicesByUser;
	    
	    
	    
//	méthode listOfDevicesByOS
 * 
	
	*/


}
