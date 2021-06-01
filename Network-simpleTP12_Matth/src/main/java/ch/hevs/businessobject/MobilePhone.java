package ch.hevs.businessobject;

import javax.persistence.CascadeType;
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
@Table(name = "MobilePhone")
public class MobilePhone extends Device {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long MobilePhoneid;

	private String name;

	public long getId() {
		return MobilePhoneid;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_OPERATIONALSYSTEM")
	private OperationalSystem osMobPhon;

	public MobilePhone() {
		super();
	}

	public MobilePhone(String name) {

		super(name);
		this.name = name;
	}

	public MobilePhone(String name, OperationalSystem os) {

		super(name);
		this.name = name;
	}

	public OperationalSystem getOs() {
		return osMobPhon;
	}

	public void setOs(OperationalSystem os) {
		this.osMobPhon = os;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}
