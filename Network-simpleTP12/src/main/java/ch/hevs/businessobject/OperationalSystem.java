package ch.hevs.businessobject;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@Table(name="OperationalSystem")
public class OperationalSystem {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long osId;
	
	@Column(name="OsName")
    private String operationalSystemName;
    
    public OperationalSystem() {}
    
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

    
    
    
   
}
