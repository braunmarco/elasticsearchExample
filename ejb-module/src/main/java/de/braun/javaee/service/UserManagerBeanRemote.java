package de.braun.javaee.service;

import java.util.List;

import javax.ejb.Remote;

import de.braun.javaee.models.User;

@Remote
public interface UserManagerBeanRemote {
	public void addUser (final User user);
	public void updateUser (final User user);
	public void removeUser (final long id);
	public void setStatus (final boolean active);
	public List<User> getUsers ();
	public User getUser (final long id);
}
