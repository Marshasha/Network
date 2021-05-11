package ch.hevs.businessobject;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Tablet extends Device {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

    public long getId() {
		return id;
	}

/*	@ManyToOne( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User owner;
*/
    
	public Tablet() {super();}

/*    public Tablet(String name, OperationalSystem os) {
        super(name, os);
        this.owner = new User();
    }

    @Override
    public void addOwner(User user) {
        owner = user;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
*/
	
}
