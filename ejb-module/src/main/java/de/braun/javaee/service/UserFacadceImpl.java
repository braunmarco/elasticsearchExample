package de.braun.javaee.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sun.javafx.Utils;

import de.braun.javaee.dao.UserDao;
import de.braun.javaee.models.User;

/**
 * Session Bean implementation class UserManagerBean
 */
@Stateless
public class UserFacadceImpl implements UserFacade {
	
	@EJB
	private UserDao userDao;
	
	@Override
	public void addUser(final User user) {
		userDao.save(user);
	}

	@Override
	public void updateUser(final User user) {
		userDao.update(user);	
	}

	@Override
	public void removeUser(final int id) {
		final User userToDelete = userDao.find(id);
		userDao.delete(userToDelete);
	}

	@Override
	public List<User> getUsers() {
		return userDao.findAll();
	}

	@Override
	public User getUser(long id) {
		// TODO Auto-generated method stub
		return userDao.findUserById(id);
	}	
}
