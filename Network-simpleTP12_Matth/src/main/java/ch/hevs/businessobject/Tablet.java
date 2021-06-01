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
@Table(name = "Tablet")
public class Tablet extends Device {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long tabletId;

	private String name;

	public long getId() {
		return tabletId;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_OPERATIONALSYSTEM")
	private OperationalSystem osTablet;

	public Tablet() {
		super();
	}

	public Tablet(String name) {

		super(name);
		this.name = name;
	}

	public Tablet(String name, OperationalSystem os) {

		super(name);
		this.name = name;
		this.osTablet = os;
	}

	public OperationalSystem getOs() {
		return osTablet;
	}

	public void setOs(OperationalSystem os) {
		this.osTablet = os;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
