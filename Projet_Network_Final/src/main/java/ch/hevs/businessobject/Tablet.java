package ch.hevs.businessobject;

import java.io.Serializable;
import java.lang.management.OperatingSystemMXBean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Tablet")
public class Tablet extends Device {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *  Mapping
	 */   
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_USER")
	private User tabletOwner;

	@ManyToOne( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_OPERATIONALSYSTEM")
	private OperationalSystem osTablet;

    /**  
     * Getters/Setters
     */
	public OperationalSystem getOs() {
		return osTablet;
	}

	public void setOs(OperationalSystem os) {
		this.osTablet = os;
		super.osDevice = osTablet;
	}
		
	public User getTabletOwner() {
		return tabletOwner;
	}

	public void setTabletOwner(User tabletOwner) {
		this.tabletOwner = tabletOwner;
	}

/*
	
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
    */
    /** 
     * Constructors 
    */
	public Tablet() {
		super();
	}

	public Tablet(String name) {
		super(name);
		
		this.name = name;
	}

	public Tablet(String name, OperationalSystem os) {
		super(name, os);

		this.name = name;
		this.osTablet = os;
	}
	
}
