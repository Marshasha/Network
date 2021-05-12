package ch.hevs.businessobject;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Device {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long deviceId;
    
    private String name;
    
    @ManyToMany
    private Set<User> owners;
   

    public String getName() {
        return name;
    }


    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDeviceId() {
        return deviceId;
    }
    
    public Set<User> getOwners() {
        return owners;
    }

    public void setOwners(Set<User> owners) {
        this.owners = owners;
    }

    
    public Device() {}

    public Device(String name){
        this.name=name;
        this.owners = new HashSet<User>();
    }

    public  void addOwner(User user) {
    	owners.add(user);
    }

 
}
