package de.braun.javaee;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import de.braun.javaee.models.User;
import de.braun.javaee.service.UserManagerBeanRemote;

@ManagedBean
@RequestScoped
public class UserManagerService {
	
	@EJB
	private UserManagerBeanRemote umb;
	
	private User user;
	
	public List <User> userList;

    @PostConstruct
    public void init() {
    	userList = umb.getUsers();
    }
    
	public List<User> getUserList() {
		return userList;
	}
	
	public String showUserList (){
		return "view_userlist";
	}
	
	public void add (User user) {
		umb.addUser(user);
	}
}
