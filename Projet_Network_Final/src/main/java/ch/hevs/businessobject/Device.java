package ch.hevs.businessobject;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Device implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id; 
	
	@Column(unique=true)
	protected String name;
    
    @Transient
    protected OperationalSystem osDevice;
    
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
    
    public void setiId(Long deviceId) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getiId() {
        return id;
    }   
    
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
    
}
