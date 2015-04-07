package de.braun.javaee.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.braun.javaee.models.User;

/**
 * Session Bean implementation class UserManagerBean
 */
@Stateless
@LocalBean
public class UserManagerBean implements UserManagerBeanRemote {
	@PersistenceContext(unitName="EjbComponentPU")
	private EntityManager entityManager;
	
	/**
     * Default constructor. 
     */
    public UserManagerBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addUser(final User user) {
		entityManager.persist(user);
	}

	@Override
	public void removeUser(final long id) {
		User user = getUser(id);
		entityManager.remove(user);
	}

	@Override
	public void setStatus(final boolean active) {
		// TODO Auto-generated method stub
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("From User").getResultList();
	}

	@Override
	public User getUser(final long id) {
		// TODO Auto-generated method stub
		return (User) entityManager.createQuery("From User where id="+id).getSingleResult();
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		entityManager.merge(user);
	}
}
