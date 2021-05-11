package ch.hevs.businessobject;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

// @Embeddable
// @Entity
// @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public /* abstract */ class Device {


//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long deviceId;
    
    private String name;
    
//    @ManyToOne( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private OperationalSystem os;
    
   
    public void setOs(OperationalSystem os) {
        this.os = os;
    }

    public String getName() {
        return name;
    }

    public OperationalSystem getOs() {
        return os;
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
    
    public Device() {}

   public Device(String name, OperationalSystem os){
        this.name=name;
        this.os = os;
    }

/*
    public abstract void addOwner(User user);

    public String toString(){
        return "This device is " + getName() + " with OS : " + getOs().getOperationalSystemName();
    }
 */
    
}
