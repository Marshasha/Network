<<<<<<< Updated upstream:Network-simpleTP12_Matth/src/main/java/ch/hevs/businessobject/User.java
package ch.hevs.businessobject;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long userId;
	
	@Column(name="loginName")
    private String loginName;
    
	@Column(name="firstName")
    private String firstName;
	
	@Column(name="lastName")
    private String lastName;
	
	/**
	 *  Mapping
	 */   
	@ManyToMany (mappedBy = "computerOwners", fetch = FetchType.LAZY)
    private Set<Computer> computers;
	
	@OneToMany (mappedBy = "phoneOwner", cascade = CascadeType.ALL)
    private Set<MobilePhone> phones;
	
	@OneToMany (mappedBy = "tabletOwner", cascade = CascadeType.ALL)
    private Set<Tablet> tablets;

    /**  
     * Getters/Setters
     */
   public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
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


    public long getUserId() {
        return userId;
    }

    public String getLoginName() {
        return loginName;
    }
   
    public Set<Computer> getComputers() {
		return computers;
	}

	public void setComputers(Set<Computer> computers) {
		this.computers = computers;
	}
	
	public Set<MobilePhone> getPhones() {
		return phones;
	}

	public void setPhones(Set<MobilePhone> phones) {
		this.phones = phones;
	}

	public Set<Tablet> getTablets() {
		return tablets;
	}

	public void setTablets(Set<Tablet> tablets) {
		this.tablets = tablets;
	}

    /** 
     * Add/Remove Device
     */	
    public void addComputer(Computer c){
    	computers.add(c);
    }

    public void removeComputer(Computer c){
    	computers.remove(c);   	
    }

    public void addMobilePhone(MobilePhone mp){
    	phones.add(mp);
    }

    public void removeMobilePhone(MobilePhone mp){
    	phones.remove(mp);   	
    }
    
    public void addTablet(Tablet t){
    	tablets.add(t);
    }

    public void removeTablet(Tablet t){
    	tablets.remove(t);   	
    }
 
    /** 
     * Constructors 
    */
	public User() {
    	this.computers = new HashSet<Computer>();
    	this.phones = new HashSet<MobilePhone>();
    	this.tablets = new HashSet<Tablet>();
 
    }

    public User (String loginName, String firstName, String lastName){
        this.loginName=loginName;
        this.firstName=firstName;
        this.lastName=lastName;
        
        this.computers = new HashSet<Computer>();
    	this.phones = new HashSet<MobilePhone>();
    	this.tablets = new HashSet<Tablet>();      
    }

}
=======
package ch.hevs.businessobject;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long userId;
	
	@Column(name="loginName", unique =true)
    private String loginName;
    
	@Column(name="firstName")
    private String firstName;
	
	@Column(name="lastName")
    private String lastName;
	
	/**
	 *  Mapping
	 */   
	@ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_Computer")
    private Set<Computer> computers;
	
	@OneToMany (mappedBy = "phoneOwner", cascade = CascadeType.ALL)
    private Set<MobilePhone> phones;
	
	@OneToMany (mappedBy = "tabletOwner", cascade = CascadeType.ALL)
    private Set<Tablet> tablets;

    /**  
     * Getters/Setters
     */
   public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
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

    public long getUserId() {
        return userId;
    }

    public String getLoginName() {
        return loginName;
    }
   
    public Set<Computer> getComputers() {
		return computers;
	}

	public void setComputers(Set<Computer> computers) {
		this.computers = computers;
	}
	
	public Set<MobilePhone> getPhones() {
		return phones;
	}

	public void setPhones(Set<MobilePhone> phones) {
		this.phones = phones;
	}

	public Set<Tablet> getTablets() {
		return tablets;
	}

	public void setTablets(Set<Tablet> tablets) {
		this.tablets = tablets;
	}

    /** 
     * Add/Remove Device
     */	
    public void addComputer(Computer c){
    	computers.add(c);
    	c.getComputerOwners().add(this);
    }

    public void removeComputer(Computer c){
    	computers.remove(c);   	
    }

    public void addMobilePhone(MobilePhone mp){
    	phones.add(mp);
    }

    public void removeMobilePhone(MobilePhone mp){
    	phones.remove(mp);   	
    }
    
    public void addTablet(Tablet t){
    	tablets.add(t);
    }

    public void removeTablet(Tablet t){
    	tablets.remove(t);   	
    }
 
    /** 
     * Constructors 
    */
	public User() {
    	this.computers = new HashSet<Computer>();
    	this.phones = new HashSet<MobilePhone>();
    	this.tablets = new HashSet<Tablet>();
 
    }

    public User (String loginName, String firstName, String lastName){
        this.loginName=loginName;
        this.firstName=firstName;
        this.lastName=lastName;
        
        this.computers = new HashSet<Computer>();
    	this.phones = new HashSet<MobilePhone>();
    	this.tablets = new HashSet<Tablet>();      
    }

}
>>>>>>> Stashed changes:Projet_Network_Final/src/main/java/ch/hevs/businessobject/User.java
