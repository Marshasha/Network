package ch.hevs.businessobject;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class OperationalSystem {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long osId;
	
    private String operationalSystemName;
    
    @OneToMany(mappedBy = "osMobPhon", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MobilePhone> phones;

    @OneToMany(mappedBy = "osTablet", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Tablet> tablets;

    @OneToMany(mappedBy = "osComputer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Computer> computers;

 /*   @OneToMany(mappedBy = "os", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Device> devices;
*/
    
    public void setOperationalSystemName(String operationalSystemName) {
        this.operationalSystemName = operationalSystemName;
    }

    public long getOsId() {
        return osId;
    }

    public String getOperationalSystemName() {
        return operationalSystemName.toString();
    }
    
    public OperationalSystem(String osName) {
    	this.operationalSystemName=osName;
    }
    
/*    public OperationalSystem() {
    	devices = new HashSet<Device>(); 
    }
    
    public void addDevice(Device d) {
    	devices.add(d);
    }
 */
    
    public OperationalSystem() {
    	phones = new HashSet<MobilePhone>();
    	tablets = new HashSet<Tablet>();    	
    	computers = new HashSet<Computer>();    	   	
    }
    
    public void addTablet(Tablet t) {
    	tablets.add(t);
    }
      
    public void addMobPhone(MobilePhone mp) {
    	phones.add(mp);
    }

    public void addDevice(Computer c) {
    	computers.add(c);
    }
   
}
