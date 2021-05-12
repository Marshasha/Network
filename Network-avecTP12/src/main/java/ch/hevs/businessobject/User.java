package ch.hevs.businessobject;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    
    @ManyToMany(mappedBy = "computerOwners", cascade = CascadeType.ALL)
    @JoinColumn(name="FK_USER")
    private Set<Computer> computers;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="FK_USER")
    private Set<MobilePhone> phones;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="FK_USER")
    private Set<Tablet> tablets;

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

	public User() {
    	this.computers = new HashSet<Computer>();
    	this.phones = new HashSet<MobilePhone>();
    	this.tablets= new HashSet<Tablet>();
    }

    public User (String loginName, String firstName, String lastName){
        this.loginName=loginName;
        this.firstName=firstName;
        this.lastName=lastName;
        computers = new HashSet<Computer>();
        phones = new HashSet<MobilePhone>();
    	tablets= new HashSet<Tablet>();
    }

    public void addComputer(Computer c){
    	computers.add(c);
    	c.addOwner(this);
    }

    public void addTablet(Tablet t){
    	tablets.add(t);
    	t.addOwner(this);
    }
    
    public void addPhone(MobilePhone phone){
    	phones.add(phone);
    	phone.addOwner(this);
    }
}
