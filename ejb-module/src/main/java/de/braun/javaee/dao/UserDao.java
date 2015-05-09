package de.braun.javaee.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.OrderBy;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.omg.PortableInterceptor.USER_EXCEPTION;

import de.braun.javaee.models.User;

@Stateless
public class UserDao extends GenericDAO<User> {

	public UserDao (){
		super (User.class);
	}
	
	public User findUserById(final long id ){
		Map <String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", id);
		
		//return super.findOneResult(User.FIND_BY_ID, parameters);
		return super.findOneResult("select u from User u where u.id = :id", parameters);
	}

	@Override
	public List<User> findAll() {
		// sortierung nach id
		return super.findAll("id");
	}
	
	/*public User findUserByEmail(final String email ){
		Map <String, Object> parameters = new HashMap<String, Object>();
		parameters.put("email", email);
		
		return super.findOneResult(User.FIND_BY_EMAIL, parameters);
	}*/
}
