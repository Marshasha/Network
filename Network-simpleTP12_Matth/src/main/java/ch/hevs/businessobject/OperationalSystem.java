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
import javax.persistence.Table;

@Entity
@Table(name = "Operationalsystem")
public class OperationalSystem {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long osId;

	private String operationalSystemName;

	@OneToMany(mappedBy = "osMobPhon", cascade = CascadeType.ALL)
	private Set<MobilePhone> phones;

	@OneToMany(mappedBy = "osTablet", cascade = CascadeType.ALL)
	private Set<Tablet> tablets;

	@OneToMany(mappedBy = "osComputer", cascade = CascadeType.ALL)
	private Set<Computer> computers;

	public void setOperationalSystemName(String operationalSystemName) {
		this.operationalSystemName = operationalSystemName;
	}

	public long getOsId() {
		return osId;
	}

	public String getOperationalSystemName() {
		return operationalSystemName.toString();
	}

	public void addTablet(Tablet t) {
		tablets.add(t);
	}

	public Set<Tablet> getTablets() {
		return this.tablets;
	}

	public void setTablets(Set<Tablet> tablets) {
		this.tablets = tablets;
	}

	public void addMobPhone(MobilePhone mp) {
		this.phones.add(mp);
	}

	public Set<MobilePhone> getMobilePhones() {
		return this.phones;
	}

	public void setMobilePhones(Set<MobilePhone> phones) {
		this.phones = phones;
	}

	public void addComputer(Computer c) {
		computers.add(c);
	}

	public Set<Computer> getComputers() {
		return this.computers;
	}

	public void setComputers(Set<Computer> computers) {
		this.computers = computers;
	}

	public OperationalSystem(String osName) {
		this.operationalSystemName = osName;

    	phones = new HashSet<MobilePhone>();
		tablets = new HashSet<Tablet>();
    	computers = new HashSet<Computer>();
	}

	public OperationalSystem() {
	}

}
