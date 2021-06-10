package ch.hevs.businessobject;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long userId;
    private String loginName;
    private String name;
    
//    @ManyToMany(mappedBy = "owners", cascade = CascadeType.ALL)
 //   private Set<Device> devices;
    
/*    @OneToMany(mappedBy = "tablet", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Device> devices;
    
*/

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public long getUserId() {
        return userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getName() {
        return name;
    }

    /*   
    public void setDevices(Set<Device> devices) {
        this.devices = devices;
    }

    public Set<Device> getDevices() {
        return devices;
    }
*/    
    public Users() {
//    	this.devices = new HashSet<Device>();
    }
    public Users (String loginName, String name){
        this.loginName=loginName;
        this.name=name;
//        devices = new HashSet<Device>();
    }
/*    
    public void addDevice(Device d){
        devices.add(d);
        d.addOwner(this);
    }
*/
}
