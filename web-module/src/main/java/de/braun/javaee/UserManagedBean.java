package de.braun.javaee;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import de.braun.javaee.models.User;
import de.braun.javaee.service.UserFacade;

@ManagedBean
@SessionScoped
public class UserManagedBean {
	@EJB
	private UserFacade umb;
	
	private User user;
	private String surname;
	private String name;
		
	public User getUser() {
		
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String add () {
		user = new User();
		user.setName(this.name);
		user.setSurname(this.surname);
		umb.addUser(user);
		
		return "view_userlist";
	}

	public String update () {
		umb.updateUser(user);
		
		return "view_userlist";
	}	
}
