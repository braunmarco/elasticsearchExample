package de.braun.javaee.service;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import de.braun.javaee.models.User;

@Local
public interface UserFacade {
	public void addUser (final User user);
	public void updateUser (final User user);
	public void removeUser (final int id);
	public List<User> getUsers ();
	public User getUser (final long id);
}
