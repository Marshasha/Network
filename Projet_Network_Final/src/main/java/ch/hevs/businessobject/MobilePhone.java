package ch.hevs.businessobject;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MobilePhone")
public class MobilePhone extends Device {
    
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *  Mapping
	 */   
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_USER")
	private User phoneOwner;
	
	@ManyToOne( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_OPERATIONALSYSTEM")
	private OperationalSystem osMobPhon;

	
    /**  
     * Getters/Setters
     */
	public OperationalSystem getOs() {
		return osMobPhon;
	}

	public void setOs(OperationalSystem os) {
		this.osMobPhon = os;
		super.osDevice = osMobPhon;
	}
	
	public User getPhoneOwner() {
		return phoneOwner;
	}

	public void setPhoneOwner(User phoneOwner) {
		this.phoneOwner = phoneOwner;
	}
 
    /** 
     * Constructors 
    */
	public MobilePhone() {
		super();		
		}

	public MobilePhone(String name) {

		super(name);
		this.name = name;
	}

	public MobilePhone(String name, OperationalSystem os) {

		super(name, os);
		this.name = name;
		this.osMobPhon = os;
	}

	
}
