package ch.hevs.businessobject;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.eclipse.persistence.descriptors.SerializableDescriptorEventHolder;

 
@Entity
@Table(name = "Computer")
public class Computer extends Device {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 *  Mapping
	 */   
	@ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_USER")
	private Set<User> computerOwners;

	
	@ManyToOne( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_OperationalSystem")
	private OperationalSystem osComputer;

	
    /**  
     * Getters/Setters
     */
	public Set<User> getComputerOwners() {
		return computerOwners;
	}

	public void setComputerOwners(Set<User> computerOwners) {
		this.computerOwners = computerOwners;
	}
	
	public void setOs(OperationalSystem os) { 
		this.osComputer = os; 
	}
	
	/** 
	 * Add/Remove User
	 */	
	public void addOwner(User user) {
		computerOwners.add(user);
    	user.getComputers().add(this);
	}
	
	public void removeComputerInOwner (User user) {
		user.removeComputer(this);
	}
	
    /** 
     * Constructors 
    */
	public Computer() { 
		super();
		
		this.computerOwners = new HashSet<User>();	
	}

	public Computer(String name) {
		super(name);
		
		this.name = name;		
		this.computerOwners = new HashSet<User>();
	}

	public Computer(String name, OperationalSystem os) {
		super(name,os);

		this.name = name;
		this.osComputer = os;		
		this.computerOwners = new HashSet<User>();
	}
}

