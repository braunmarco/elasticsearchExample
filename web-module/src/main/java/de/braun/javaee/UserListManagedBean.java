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
public class UserListManagedBean {

	@EJB
	private UserManagerBeanRemote umb;

	private User selectedUser;
	private List<User> userList;

	@PostConstruct
	public void init() {
		userList = umb.getUsers();
	}

	public List<User> getUserList() {
		return userList;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	/* JSF-Path */
	public String showUserList() {
		return "view_userlist";
	}

	public String addUserToList() {
		return "inputUserDetails";
	}

	public String updateUserDetails() {
		return "updateUserDetails";
	}
}
