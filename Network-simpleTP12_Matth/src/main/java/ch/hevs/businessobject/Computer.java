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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

 
@Entity
@Table(name = "Computer")
public class Computer extends Device {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long computerId;

	@Column(name = "Name")
	private String name;

	@ManyToMany
	private Set<User> computerOwners;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_OPERATIONALSYSTEM")
	private OperationalSystem osComputer;

	public Computer() { super();}

	public long getComputerId() {
		return computerId;
	}

	public Computer(String name) {

		this.name = name;
		this.computerOwners = new HashSet<User>();

	}

	public void addOwner(User user) {
		System.out.println(this.name + " has the owner " + user.getLoginName());
		computerOwners.add(user);
	}

	public Set<User> getComputerOwners() {
		return computerOwners;
	}

	public void setComputerOwners(Set<User> computerOwners) {
		this.computerOwners = computerOwners;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OperationalSystem getOs() { return osComputer; }
	
	
	public void setOs(OperationalSystem os) { this.osComputer = os; }
	 

}

