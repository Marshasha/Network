package ch.hevs.businessobject;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
    private long userId;
	
    private String loginName;
   
    private String name;
   

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }


    public long getUserId() {
        return userId;
    }

    public String getLoginName() {
        return loginName;
    }


    public User (){ }

   

}
