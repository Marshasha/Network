package ch.hevs.businessobject;

import java.io.Serializable;

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
	 *  Mapping
	 */   
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private User tabletOwner;

	@ManyToOne( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
