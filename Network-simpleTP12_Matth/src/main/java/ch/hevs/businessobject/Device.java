package ch.hevs.businessobject;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

//@Embeddable
@Entity
//@Table(name = "Device")
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name="Device_type")
public class Device implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id; 
	
    protected String name;
    
    @Transient
 //   @ManyToOne( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected OperationalSystem osDevice;
    
/*    @Transient
    private User user;
    @Transient
	private Set<User> deviceOwners;
*/
    

   /**Getters/Setters
    * 
    * @param os
    */
   public void setOs(OperationalSystem os) {
        this.osDevice = os;
    }
    public OperationalSystem getOs() {
        return osDevice;
    }

    public String getName() {
        return name;
    }
    
    public void setDeviceId(Long deviceId) {
        this.id = deviceId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDeviceId() {
        return id;
    }   
 /*   
    public User getUser() {
		return user;
	}y

	public void setUser(User user) {
		this.user = user;
	}

	public Set<User> getDeviceOwners() {
		return deviceOwners;
	}

	public void setDeviceOwners(Set<User> deviceOwners) {
		this.deviceOwners = deviceOwners;
	}
*/
    
	/** Contructors
	 * 
	 */
	public Device() {}
    
    public Device(String name){
        this.name=name;
    }


   public Device(String name, OperationalSystem os ){
        this.name=name;
        this.osDevice = os;
    }

/*
    public abstract void addOwner(User user);

    public String toString(){
        return "This device is " + getName() + " with OS : " + getOs().getOperationalSystemName();
    }
 */
    
}
